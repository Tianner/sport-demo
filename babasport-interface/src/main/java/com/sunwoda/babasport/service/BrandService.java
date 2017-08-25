package com.sunwoda.babasport.service;

import com.sunwoda.babasport.common.utils.Page;
import com.sunwoda.babasport.pojo.Brand;

import java.util.List;

/**
 * 品牌业务层
 * Created by Administrator on 2017/8/14.
 */
public interface BrandService {

    /**
     * 根据样例同时附带分页查询信息
     * @param brand
     * @return
     */
    List<Brand> findByExample(Brand brand);  /**
     * 根据样例同时附带分页查询信息
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Brand> findByExample(Brand brand, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找品牌
     * @param id
     * @return
     */
    public Brand findBrandById(Long id);

    /**
     * 更新brand
     * @param brand
     * @return 1造成改变,0未造成改变
     */
    public Integer update(Brand brand);

    /**
     * 根据id集合批量删除
     * @param ids
     */
    public void deleteBatch(Integer[] ids);

    /**
     * 根据id删除一条数据
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 添加品牌
     * @param brand
     */
    public void add(Brand brand);
}
