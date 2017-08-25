package com.sunwoda.babasport.product.service;

import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Brand;
import com.sunwoda.babasport.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BrandServiceImplTest {
    @Autowired
    private BrandService brandService;

    /**
     * 测试findByExample
     * @throws Exception
     */
    @Test
    public void findByExample() throws Exception {
        Brand brand = new Brand();
        Page<Brand> page = this.brandService.findByExample(brand, null, null);
        for (Brand b:page.getResult()) {
            System.out.println(b);
        }
    }
    @Test
    public void findByExamp() throws Exception {
        Brand brand = this.brandService.findBrandById(1L);
        System.err.println(brand);
    }

}