package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.pojo.Brand;
import com.sunwoda.babasport.pojo.Sku;
import com.sunwoda.babasport.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月19日 20:54.
 */
@Controller
public class SkuAction {
    @Autowired
    @Qualifier("skuService")
    private SkuService skuService;

    @RequestMapping("console/sku/list.do")
    public String consoleShowSku(Model model,Sku sku){
        List<Sku> skus = this.skuService.findSkuByBrandId(sku);
        model.addAttribute("skus",skus);
        return "/sku/list";
    }

    /**
     * 更新市场价，售价，库存，购买限制，运费
     * @param sku
     * @return
     */
    @RequestMapping("console/sku/updateSku.do")
    @ResponseBody
    public Boolean updateSku(Sku sku){
        System.out.println("正在更新-----------");
        try {
            this.skuService.updateSku(sku);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
