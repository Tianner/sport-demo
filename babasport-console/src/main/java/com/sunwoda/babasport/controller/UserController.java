package com.sunwoda.babasport.controller;

import com.sunwoda.babasport.pojo.Buyer;
import com.sunwoda.babasport.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关action类
 * Created by Administrator on 2017/8/14.
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 添加新用户
     * @param m
     * @param user
     * @return
     */
    @RequestMapping("save")
    public String save(Model m, Buyer user){
        user.setUsername("王尼玛");
        this.userService.add(user);
        m.addAttribute("msg","你好世界");
        return "index";
    }
}
