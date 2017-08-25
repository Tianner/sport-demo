package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.common.fastDFS.FastDFSTools;
import com.sunwoda.babasport.common.utils.Encoding;
import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Brand;
import com.sunwoda.babasport.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
public class BrandAction {
    @Autowired
    @Qualifier("brandService")
    private BrandService brandService;

    /**
     * 通用页面跳转
     * @param page
     * @return
     */
    @RequestMapping("console/brand/{page}.do")
    public String consoleBrandPage(@PathVariable("page")String page){
        return "/brand/"+page;
    }

    /**
     * 转发brand列表list.jsp页面
     * @param model
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "console/brand/list.do")
    public String consoleBrandShow(Model model,Brand brand, Integer pageNum, Integer pageSize){
            if(brand.getName()!=null){
                brand.setName(Encoding.encodeGetRequest(brand.getName()));
            }
            Page<Brand> page = this.brandService.findByExample(brand,pageNum,pageSize);
            model.addAttribute("brandPage",page);
            model.addAttribute("isDisplay",brand.getIsDisplay());
            model.addAttribute("name",brand.getName());
            System.err.println(page.getResult());
        return "/brand/list";
    }

    /**
     * 添加品牌,成功则跳转至列表，失败则回到添加页面
     * @param brand
     * @return
     */
    @RequestMapping("console/brand/doAdd.do")
    public String consoleBrandAdd(Brand brand,Model  model){
        System.out.println(brand+"---------");
        try {
            this.brandService.add(brand);

            return "redirect:list.do";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("allUrl",FastDFSTools.ROOT_PATH+brand.getImgUrl());
            model.addAttribute("brand",brand);
            return "brand/add";
        }
    }
    /**
     *  修改品牌信息
     * @param model
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "console/brand/edit.do")
    public String consoleBrandEdit(Model model,Brand brand, Integer pageNum, Integer pageSize){
        Brand b = this.brandService.findBrandById(brand.getId());
        model.addAttribute("brand" ,b);
        model.addAttribute("pageBrand",brand);
        System.out.println("======"+brand);
        System.out.println("======"+pageNum);
        System.out.println("======"+pageSize);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("pageSize",pageSize);
        return "/brand/edit";
    }

    /**
     * 提交Brand修改，并转发页面到list页面
     * @param brand
     * @param model
     * @param pageNum
     * @param pageSize
     * @param name0
     * @param isDisplay0
     * @return
     */
    @RequestMapping(value = "console/brand/doEdit.do")
    public String consoleBrandDoEdit(Brand brand,Integer pageNum, Integer pageSize,String name0,Integer isDisplay0){
        try {
            if(StringUtils.isNotBlank(brand.getImgUrl())){
                brand.setImgUrl(brand.getImgUrl().replace(FastDFSTools.ROOT_PATH,""));
            }
            this.brandService.update(brand);
            System.err.println("1------"+brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "redirect:list.do?name="+(name0==null?"":name0) +"&isDisplay="+(isDisplay0 == null?"":isDisplay0)+"&pageNum="+pageNum+"&pageSize="+pageSize;
        return url;
    }

    /**
     * 根据id批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "console/brand/deleteBatch.do")
    public String deleteBatch(@RequestParam("id")Integer[] ids,Integer pageNum, Integer pageSize,String name,Integer isDisplay){
        System.out.println(Arrays.toString(ids));
        try {
            this.brandService.deleteBatch(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "redirect:list.do?name="+(name==null?"":name) +"&isDisplay="+(isDisplay == null?"":isDisplay)+"&pageNum="+pageNum+"&pageSize="+pageSize;
        return url;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping("console/brand/deleteById.do")
    public String deleteById(@RequestParam("id")Integer id,Integer pageNum, Integer pageSize,String name,Integer isDisplay){
        try {
            this.brandService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        String url = "redirect:list.do?name="+(name==null?"":name) +"&isDisplay="+(isDisplay == null?"":isDisplay)+"&pageNum="+pageNum+"&pageSize="+pageSize;
        return url;
    }
}
