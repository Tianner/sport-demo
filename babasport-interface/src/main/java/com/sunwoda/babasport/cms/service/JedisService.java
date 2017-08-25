package com.sunwoda.babasport.cms.service;

import com.sunwoda.babasport.pojo.Brand;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月21日 16:06.
 */
public interface JedisService {
    /**
     * 查询redis中所有品牌
     * @return
     */
    public Map<String, String> findAllBrand();
}
