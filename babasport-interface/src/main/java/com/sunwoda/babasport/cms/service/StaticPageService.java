package com.sunwoda.babasport.cms.service;

import com.sunwoda.babasport.pojo.SuperPojo;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 描述:页面静态化业务接口
 * 创建者:田海波 于 2017年08月25日 20:18.
 */
public interface StaticPageService {
    /**
     * 根据商品详情pojo对象创建静态页面
     * @param id
     * @param superPojo
     */
    public void createStaticPageOfProduct(Long id,SuperPojo superPojo) throws IOException, TemplateException;

    /**
     * 删除静态页面
     * @param id
     */
    public void deleteStaticPageOfProduct(Long id);
}
