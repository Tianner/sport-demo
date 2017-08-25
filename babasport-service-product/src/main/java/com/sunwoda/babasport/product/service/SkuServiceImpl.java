package com.sunwoda.babasport.product.service;

import com.github.abel533.entity.Example;
import com.sunwoda.babasport.pojo.Sku;
import com.sunwoda.babasport.product.mapper.SkuMapper;
import com.sunwoda.babasport.cms.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月19日 1:02.
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void add(Sku sku) {
        this.skuMapper.insert(sku);
    }

    /*@Override
    public List<Sku> findSkuByBrandId(Sku sku) {
        Example example = new Example(Sku.class);
        if(sku.getProductId()!=null){
            example.createCriteria().andEqualTo("productId",sku.getProductId());
        }
        List<Sku> list  = this.skuMapper.selectByExample(example);
        return list;
    } */
    @Override
    public List<Sku> findSkuByBrandId(Sku sku) {
       List<Sku> list = this.skuMapper.findSkuByProductId(sku);
        return list;
    }

    @Override
    public void updateSku(Sku sku) {
        Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("id",sku.getId());
        this.skuMapper.updateByExampleSelective(sku,example);
    }
}
