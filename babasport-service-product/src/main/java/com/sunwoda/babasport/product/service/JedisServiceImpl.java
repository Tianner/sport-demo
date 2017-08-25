package com.sunwoda.babasport.product.service;

import com.sunwoda.babasport.service.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月21日 16:07.
 */
@Service("jedisService")
public class JedisServiceImpl implements JedisService {

    @Autowired
    @Qualifier("jedis")
    private Jedis jedis;

    @Override
    public Map<String, String> findAllBrand() {
        return this.jedis.hgetAll("brand");
    }
}
