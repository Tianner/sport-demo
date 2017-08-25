package com.sunwoda.babasport.cms.service;

import com.sunwoda.babasport.common.fastDFS.FastDFSTools;
import com.sunwoda.babasport.pojo.SuperPojo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:页面静态化业务实现类
 * 创建者:田海波 于 2017年08月25日 20:20.
 */
@Service("staticPageService")
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware {
    private ServletContext servletContext;
    @Autowired
    @Qualifier("freeMarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void createStaticPageOfProduct(Long id,SuperPojo superPojo) throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("product.html");

        String realPath = this.servletContext.getRealPath("/html/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        File pageFile = new File(realPath,id + ".html");
        pageFile.createNewFile();
        Writer out = new FileWriter(pageFile);
        Map<String, Object> map = new HashMap<>();
       // map.put("detail","田海波");
        map.put("detail",superPojo);
        map.put("baseAddr", FastDFSTools.ROOT_PATH);
       /* map.put("product",superPojo.get("product"));
        map.put("brandName",superPojo.get("brandName"));
        map.put("skuList",superPojo.get("skuList"));*/
        template.process(map,out);
    }

    @Override
    public void deleteStaticPageOfProduct(Long id) {
        String realPath = this.servletContext.getRealPath("/html/");
        File file = new File(realPath,id+".html");
        System.out.println("删除文件--"+file.getAbsolutePath());
        if(file.exists()){
            file.delete();
        }
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext =servletContext;
    }
}
