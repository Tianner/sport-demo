package com.sunwoda.babasport.product.mapper;

import com.github.abel533.mapper.Mapper;
import com.sunwoda.babasport.pojo.Sku;
import com.sunwoda.babasport.pojo.SuperPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月19日 1:03.
 */
public interface SkuMapper extends Mapper<Sku> {
    public List<Sku> findSkuByProductId(Sku sku);

    List<SuperPojo> selectByProductIdAndPriceAsc(@Param("productId") Long productId);
}
