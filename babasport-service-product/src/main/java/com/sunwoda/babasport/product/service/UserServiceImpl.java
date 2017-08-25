package com.sunwoda.babasport.product.service;

import com.sunwoda.babasport.pojo.Buyer;
import com.sunwoda.babasport.product.mapper.UserMapper;
import com.sunwoda.babasport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/8/14.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Buyer user) {
        this.userMapper.add(user);
    }
}
