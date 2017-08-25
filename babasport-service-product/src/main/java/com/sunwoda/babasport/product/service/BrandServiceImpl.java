package com.sunwoda.babasport.product.service;

import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.common.utils.PageHelper;
import com.sunwoda.babasport.pojo.Brand;
import com.sunwoda.babasport.product.mapper.BrandMapper;
import com.sunwoda.babasport.cms.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl  implements BrandService{
    @Autowired
    @Qualifier("brandMapper")
    private BrandMapper brandMapper;

    @Autowired
    @Qualifier("jedis")
    private Jedis jedis;

    @Override
    public List<Brand> findByExample(Brand brand) {
        return  this.brandMapper.findByExample(brand);
    }

    @Override
    public Page<Brand> findByExample(Brand brand, Integer pageNum, Integer pageSize) {
        System.err.println(brand+"----");
        PageHelper.startPage(pageNum, pageSize);
        this.brandMapper.findByExample(brand);
        Page page = PageHelper.endPage();
        return page;
    }

    @Override
    public Brand findBrandById(Long id) {
        Brand brand = this.brandMapper.findBrandById(id);
        return brand;
    }

    @Override
    public Integer update(Brand brand) {
        System.err.println(brand+"-------------");
        return this.brandMapper.update(brand);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        this.brandMapper.deleteBatch(ids);

    }

    @Override
    public void deleteById(Integer id) {
        this.brandMapper.deleteById(id);
    }

    @Override
    public void add(Brand brand) {
        this.brandMapper.insert(brand);
        this.jedis.hset("brand",brand.getId()+"",brand.getName()+"");
    }
}
