package com.sunwoda.babasport.cms.service;

import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.SuperPojo;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月21日 17:35.
 */
public interface SolrService {
    /**
     * 根据关键字查询索引库中的商品信息
     * @param keyword
     * @param pL
     * @param pageNum
     * @param pageSize @return
     */
    public Page<SuperPojo> findByKeyword(String keyword, String sortByPrice, Integer bradnId, Integer pL, Integer pH, Integer pageNum, Integer pageSize) throws SolrServerException;

    /**
     * 添加或删除商品到索引库
     * @param ids
     * @param isShow
     */
    public void addOrDelProductSolr(Long[] ids,Integer isShow);
}
