package com.sunwoda.babasport.pojo;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 描述:超级实体类
 * 创建者:田海波 于 2017年08月21日 20:50.
 */
public class SuperPojo extends TreeMap<String,Object> implements Serializable {
    public SuperPojo setProperty(String key,Object value){
        this.put(key,value);
        return this;
    }
}
