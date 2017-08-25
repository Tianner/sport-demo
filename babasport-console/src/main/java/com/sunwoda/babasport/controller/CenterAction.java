package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.common.utils.Encoding;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Brand;
import com.sunwoda.babasport.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/8/14.
 */
@Controller
public class CenterAction {
    @Autowired
    @Qualifier("brandService")
    private BrandService brandService;
    /**
     * 转发框架页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/{pageName}.do")
    public String consoleShow(@PathVariable("pageName")String pageName){
        return pageName;
    }


    /**
     * 转发ad页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/ad/{pageName}.do")
    public String consleAdShow(@PathVariable("pageName")String pageName){
        return "/ad/"+pageName;
    }


    /**
     * 转发frame页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/frame/{pageName}.do")
    public String consoleFrameShow(@PathVariable("pageName")String pageName){

        return "/frame/"+pageName;
    }

    /**
     * 转发order订单页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/order/{pageName},do")
    public String consoleOrderShow(@PathVariable("pageName")String pageName){
        return "/order/"+pageName;
    }

    /**
     * 转发位置页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/position/{pageName}.do")
    public String consolePositionShow(@PathVariable("pageName")String pageName){
        return "/position/"+pageName;
    }

    /**
     * 转发product商品页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/product/{pageName}.do")
    public String consoleProductShow(@PathVariable("pageName")String pageName,Model model){
        return "/product/"+pageName;
    }
}
