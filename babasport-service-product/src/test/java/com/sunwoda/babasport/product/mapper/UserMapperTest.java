package com.sunwoda.babasport.product.mapper;

import com.sunwoda.babasport.pojo.Buyer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void add() throws Exception {
        Buyer b = new Buyer();
        b.setUsername("boy1");
        b.setPassword("123456");
        b.setGender(0);
        b.setEmail("1235093386@qq.com");
        b.setRealName("田海波1");
        b.setProvince("河南省");
        b.setCity("驻马店市");
        b.setTown("正阳县");
        b.setAddr("真阳镇邹楼村");
        b.setIsDel(1);
        this.userMapper.add(b);
    }

}