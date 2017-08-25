package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:首页控制器
 * 创建者:田海波 于 2017年08月20日 17:52.
 */
@Controller
public class IndexAction {

    @RequestMapping("/")
    public String protalIndex(){
        System.out.println("-------访问首页");
        Product product = new Product();
        return "index";
    }

}
