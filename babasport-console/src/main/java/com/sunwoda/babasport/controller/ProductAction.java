package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.common.fastDFS.FastDFSTools;
import com.sunwoda.babasport.common.utils.Encoding;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Product;
import com.sunwoda.babasport.cms.service.JedisService;
import com.sunwoda.babasport.cms.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月18日 17:34.
 */
@Controller
public class ProductAction {
    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Autowired
    @Qualifier("jedisService")
    private JedisService jedisService;


    /**
     * 返回商品分页列表页面
     * @param model
     * @param product
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("console/product/list.do")
    public String consoleShowProduct(Model model,Product product,Integer pageNum, Integer pageSize){
        if(StringUtils.isNotBlank(product.getName())){
            product.setName(Encoding.encodeGetRequest(product.getName()));
        }
        System.out.println("-----web层开始查询");
        Page<Product> page = this.productService.findByExample(product,pageNum,pageSize);
        System.out.println("---"+page);
        for (Product p:page.getResult()) {
            if(StringUtils.isNotBlank(p.getImgUrl())){
                p.setImgUrl(FastDFSTools.ROOT_PATH+p.getImgUrl().split(",")[0]);
            }
        }
        model.addAttribute("productPage",page);
        model.addAttribute("isShow",product.getIsShow());
        model.addAttribute("brandId",product.getBrandId());
        model.addAttribute("name",product.getName());
        //查询品牌信息
        Map<String, String> brands = this.jedisService.findAllBrand();
        model.addAttribute("brands",brands);
        return "/product/list";
    }

    /**
     * 根据id删除商品
     * @param product
     * @param pageNum
     * @param pageSize
     * @param id0
     * @return
     */
    @RequestMapping(value = "console/product/deleteProductById.do")
    public String deleteProductById(Product product,Integer pageNum,Integer pageSize,Long id0){
        System.out.println(product);
        System.out.println(id0+"---删除id");
        try {
            this.productService.deleteProductById(id0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "redirect:list.do"
                +"?isShow="+(product.getIsShow()==null?"":product.getIsShow())
                +"&name="+(product.getName()==null?"":product.getName())
                +"&brandId="+(product.getBrandId()==null?"":product.getBrandId())
                +"&pageNum="+(pageNum == null?"":pageNum)
                +"&pageSize="+(pageSize == null?"":pageSize);
        System.err.println(url);
        return url;
    }
    @RequestMapping("console/product/add.do")
    public String consoleBrandAdd(Model model){
        Map<String, String> brands = this.jedisService.findAllBrand();
        model.addAttribute("brands",brands);
        return "product/add";
    }
    /**
     * 添加商品
     * @param product
     * @return
     */
    @RequestMapping("console/product/goAdd.do")
    public String add(Product product){
        System.err.println(product);
        if(StringUtils.isNotBlank(product.getImgUrl())){
            product.setImgUrl(product.getImgUrl().replaceAll(FastDFSTools.ROOT_PATH,""));
        }
        try {
            product.setCreateTime(new Date());
            if(product.getIsShow() == null){
                product.setIsShow(0);
            }
            if(product.getIsCommend() == null){
                product.setIsCommend(0);
            }
            if(product.getIsDel() == null){
                product.setIsDel(0);
            }
            if(product.getIsHot() == null){
                product.setIsHot(0);
            }
            if(product.getIsNew() == null){
                product.setIsNew(0);
            }
            this.productService.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list.do";
    }
    @RequestMapping("console/product/changeIsShow.do")
    @ResponseBody
    public Boolean consoleChangeIsShow(Long[] ids,Product product){
        System.out.println(Arrays.toString(ids)+"--"+product);
        try {
            this.productService.updateProduct(ids,product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
