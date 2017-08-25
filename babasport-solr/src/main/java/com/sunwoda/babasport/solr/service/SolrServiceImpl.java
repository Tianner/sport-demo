package com.sunwoda.babasport.solr.service;

import com.github.abel533.entity.Example;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.common.utils.PageHelper;
import com.sunwoda.babasport.pojo.Product;
import com.sunwoda.babasport.pojo.Sku;
import com.sunwoda.babasport.product.mapper.ProductMapper;
import com.sunwoda.babasport.product.mapper.SkuMapper;
import com.sunwoda.babasport.service.ProductService;
import com.sunwoda.babasport.service.SolrService;
import com.sunwoda.babasport.pojo.SuperPojo;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 描述:Solr搜索业务处理层
 * 创建者:田海波 于 2017年08月21日 17:39.
 */
@Service("solrService")
public class SolrServiceImpl implements SolrService {

    @Autowired
    @Qualifier("solrServer")
    private SolrServer solrServer;

    @Autowired
    @Qualifier("productMapper")
    private ProductMapper productMapper;

    @Autowired
    @Qualifier("skuMapper")
    private SkuMapper skuMapper;

    @Override
    public Page<SuperPojo> findByKeyword(String keyword, String sortByPrice, Integer brandId, Integer pL, Integer pH, Integer pageNum, Integer pageSize) throws SolrServerException {
        PageHelper.startPage(pageNum,pageSize);
        Page<SuperPojo> page = PageHelper.getPage();
        SolrQuery solrQuery = new SolrQuery("tian_name:"+keyword);
        solrQuery.setRows(page.getPageSize());
        solrQuery.setStart(page.getStartRow());
        //品牌查询
        if(brandId!=null){
            System.err.println("添加品牌过滤---"+brandId);
            solrQuery.addFilterQuery("tian_brandId:"+brandId);
            System.err.println("添加品牌过滤---");
        }
        //价格排序
        if(StringUtils.isNotBlank(sortByPrice)){
            solrQuery.setSort(new SolrQuery.SortClause("tian_price",sortByPrice));
        }
        //价格区间
        if(pL!=null && pH!=null){
            solrQuery.addFilterQuery("tian_price:"+"["+pL+" TO "+(pH == -1?"*":pH)+"]");
        }
        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("tian_name");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        QueryResponse query = this.solrServer.query(solrQuery);
        // 获得高亮数据集合
        Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
        // 获得结果集
        SolrDocumentList results = query.getResults();
        List<SuperPojo> result = new ArrayList<>();
        System.out.println("size="+results.size());
        for (SolrDocument document:results) {
            SuperPojo solrProduct = new SuperPojo();
            String id = (String)document.get("id");
            solrProduct.setProperty("id",id);
            Map<String, List<String>> map = highlighting.get(id);
            String string = map.get("tian_name").get(0);
            solrProduct.setProperty("tian_name",string);
            solrProduct.setProperty("tian_brandId",document.get("tian_brandId"));
            solrProduct.setProperty("tian_imgUrl",document.get("tian_imgUrl"));
            solrProduct.setProperty("tian_price",document.get("tian_price"));
            result.add(solrProduct);
            System.out.println("superPojo="+solrProduct);
        }
        PageHelper.endPage();
        page.setResult(result);
        page.setTotal(results.getNumFound());
        page.setPages(page.getPages());
        return page;
    }

    @Override
    public void addOrDelProductSolr(Long[] ids,Integer isShow) {
        //从索引库中删除
        if(isShow.equals(0)){
            List<String> list = new ArrayList<>();
            for (Long l:ids) {
                list.add(l+"");
            }
            try {
                synchronized (SolrServiceImpl.class) {
                this.solrServer.deleteById(list);
                    this.solrServer.commit();
                }
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //添加到solr索引库
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id",Arrays.asList((Object[])ids));
        try {
            List<Product> products = this.productMapper.selectByExample(example);
            //加同步锁，防止向solr服务提交时发生并发问题
            synchronized (SolrServiceImpl.class) {
                for (Product p : products) {
                    Example example2 = new Example(Sku.class);
                    example2.createCriteria().andEqualTo("productId", p.getId());
                    example2.setOrderByClause("price asc");
                    PageHelper.startPage(1, 1);
                    this.skuMapper.selectByExample(example2);
                    Page page = PageHelper.endPage();
                    Sku sku = (Sku) page.getResult().get(0);
                    SolrInputDocument doc = new SolrInputDocument();
                    doc.addField("id", p.getId() + "");
                    doc.addField("tian_name", p.getName());
                    doc.addField("tian_brandId", p.getBrandId());
                    doc.addField("tian_price", sku.getPrice());
                    doc.addField("tian_imgUrl", p.getImgUrl().split(",")[0]);
                    this.solrServer.add(doc);
                    System.out.println("-----" + doc.toString());
                }
                this.solrServer.commit();
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
