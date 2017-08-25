package com.sunwoda.babasport.product.service;

import com.sunwoda.babasport.pojo.Buyer;
import com.sunwoda.babasport.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Test
    public void add() throws Exception {
        Buyer b = new Buyer();
        b.setUsername("boy");
        b.setPassword("123456");
        b.setGender(0);
        b.setEmail("1135093386@qq.com");
        b.setRealName("田海波");
        b.setProvince("河南省");
        b.setCity("驻马店市");
        b.setTown("正阳县");
        b.setAddr("真阳镇邹楼村");
        b.setIsDel(1);
        this.userService.add(b);
    }

}