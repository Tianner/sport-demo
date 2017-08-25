package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.common.fastDFS.FastDFSTools;
import com.sunwoda.babasport.common.utils.Encoding;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.cms.service.ProductService;
import com.sunwoda.babasport.cms.service.SolrService;
import com.sunwoda.babasport.pojo.SuperPojo;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * 描述:页面搜索控制器
 * 创建者:田海波 于 2017年08月21日 17:36.
 */
@Controller
public class SolrAction {
    @Autowired
    @Qualifier("solrService")
    private SolrService solrService;

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Autowired
    @Qualifier("jedis")
    private Jedis jedis;

    /**
     * 页面关键字搜索商品
     * @param model
     * @return
     */
    @RequestMapping("portal/search")
    public String searchByKeyword(Model model,String keyword,Integer pageNum,Integer pageSize,String sortByPrice,Integer brandId,Integer pL,Integer pH){
        keyword = Encoding.encodeGetRequest(keyword);
        System.out.println("进行搜索-----"+keyword);
        try {
            Page<SuperPojo> page = this.solrService.findByKeyword(keyword,sortByPrice,brandId,pL,pH,pageNum,pageSize);
            //查询品牌
            Map<String, String> brands = jedis.hgetAll("brand");
            model.addAttribute("pageProduct" ,page);
            model.addAttribute("keyword",keyword);
            model.addAttribute("baseAddr", FastDFSTools.ROOT_PATH);
            model.addAttribute("sortByPrice",sortByPrice);
            model.addAttribute("brands",brands);
            model.addAttribute("brandId",brandId);
            System.err.println("brandId="+brandId+",brandName1="+brands.get(brandId+""));
            model.addAttribute("brandName",brands.get(brandId+""));
            model.addAttribute("pL",pL);
            model.addAttribute("pH",pH);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return "search";
    }

    /**
     * 点击进入商品详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("portal/product")
    public String showProduct(Long id,Model model){
        SuperPojo detail = this.productService.findProductDetailById(id);
        model.addAttribute("detail",detail);
        model.addAttribute("baseAddr",FastDFSTools.ROOT_PATH);
        return "product";
    }

}
