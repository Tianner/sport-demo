package com.sunwoda.babasport.service;

import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Product;
import com.sunwoda.babasport.pojo.SuperPojo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月18日 17:44.
 */
public interface ProductService {
    /**
     * 分页查询上篇列表信息
     * @param product
     * @param pageNum
     *@param pageSize @return
     */
    public Page<Product> findByExample(Product product, Integer pageNum, Integer pageSize);

    /**
     * 根据id删除商品
     * @param id
     */
    public void deleteProductById(Long id);

    /**
     * 添加商品
     * @param product
     */
    Integer add(Product product);

    /**
     * 更新集合中id对应商品的属性
     * @param ids
     * @param product
     */
    public void updateProduct(Long[] ids, Product product) throws IOException, SolrServerException;

    public SuperPojo findProductDetailById(Long id);
}
