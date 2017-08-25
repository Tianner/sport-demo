package com.sunwoda.babasport.product.mapper;

import com.github.abel533.mapper.Mapper;
import com.sunwoda.babasport.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌DAO层CRUD操作
 * Created by Administrator on 2017/8/14.
 */
public interface BrandMapper extends Mapper<Brand>{
    /**
     * 通过Example样例查找品牌
     * @param brand
     * @return
     */
    public List<Brand> findByExample(Brand brand);

    /**
     * 根据id查找品牌
     * @param id
     * @return
     */
    public Brand findBrandById(@Param("id")Long id);

    /**
     * 更新操作
     * @param brand
     * @return
     */
    public Integer update(Brand brand);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteBatch(@Param("ids") Integer[] ids);

    /**
     * 根据id删除数据
     * @param id
     */
    public void deleteById(Integer id);
}
