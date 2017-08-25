package com.sunwoda.babasport.product.service;

import com.github.abel533.entity.Example;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.common.utils.PageHelper;
import com.sunwoda.babasport.pojo.Product;
import com.sunwoda.babasport.pojo.Sku;
import com.sunwoda.babasport.pojo.SuperPojo;
import com.sunwoda.babasport.product.mapper.ProductMapper;
import com.sunwoda.babasport.product.mapper.SkuMapper;
import com.sunwoda.babasport.service.ProductService;
import com.sunwoda.babasport.product.service.activemq.MyQueueSender;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月18日 17:45.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    @Qualifier("jedis")
    private Jedis jedis;

    @Autowired
    @Qualifier("myQueueSender")
    private MyQueueSender myQueueSender;

    @Override
    public Page<Product> findByExample(Product product, Integer pageNum, Integer pageSize) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        if(product.getIsShow()!=null){
            criteria.andEqualTo("isShow",product.getIsShow());
        }
        if(product.getBrandId()!=null){
            criteria.andEqualTo("brandId",product.getBrandId());
        }
        if(StringUtils.isNotBlank(product.getName())){
            criteria.andLike("name","%"+product.getName()+"%");
        }
        example.setOrderByClause("createTime desc");
        PageHelper.startPage(pageNum,pageSize);
        this.productMapper.selectByExample(example);
        Page page = PageHelper.endPage();
        return page;
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        this.productMapper.delete(product);
    }

    @Override
    public Integer add(Product product) {
        //redis管理主键
        Long id =jedis.incr("productId");
        System.out.println("---"+id);
        product.setId(id);
        Integer result =this.productMapper.insertSelective(product);
        System.err.println(product.getId()+"---------");
        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");
        for (String color: colors) {
            Long c = Long.parseLong(color);
            for (String size:sizes) {
                Sku sku = new Sku();
                sku.setColorId(c);
                sku.setCreateTime(new Date());
                sku.setDeliveFee(9.9f);
                sku.setMarketPrice(199f);
                sku.setPrice(99f);
                sku.setProductId(product.getId());
                sku.setUpperLimit(10);
                sku.setStock(999);
                sku.setSize(size);
                this.skuMapper.insert(sku);
            }
        }
        return result;
    }

    @Override
    public void updateProduct(Long[] ids, Product product) throws IOException, SolrServerException {
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id",Arrays.asList((Object[])ids));

        this.productMapper.updateByExampleSelective(product,example);
        Map<String, Object> map = new HashMap<>();
        map.put("isShow",product.getIsShow());
        map.put("ids",Arrays.asList(ids));
        System.out.println(map.size()+"------");
        this.myQueueSender.sendMapMessage("productIds",map);
    }

    @Override
    public SuperPojo findProductDetailById(Long id) {
        SuperPojo superPojo = new SuperPojo();
        Product product = this.productMapper.selectByPrimaryKey(id);
        String brandName = this.jedis.hget("brand",product.getBrandId()+"");
        //查询库存并且价格升序排列
       /* Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("productId",id);
        example.setOrderByClause("price asc");*/
        List<SuperPojo> skuList = this.skuMapper.selectByProductIdAndPriceAsc(id);
        for (SuperPojo s:skuList) {
            System.err.println(s);
        }
        superPojo.setProperty("product",product);
        superPojo.setProperty("brandName",brandName);
        superPojo.setProperty("skuList",skuList);
        return superPojo;
    }
}
