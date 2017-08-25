package com.sunwoda.babasport.cms.service;

import com.sunwoda.babasport.pojo.Sku;

import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月19日 1:00.
 */
public interface SkuService {
    /**
     * 添加库存
     * @param sku
     */
    public void add(Sku sku);

    /**
     * 条件查询Sku库存数据
     * @param sku
     * @return
     */
    public List<Sku> findSkuByBrandId(Sku sku);

    public void updateSku(Sku sku);
}
