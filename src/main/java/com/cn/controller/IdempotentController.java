package com.cn.controller;

import com.cn.annotation.ApiIdempotent;
import com.cn.common.response.ServerResponse;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 17:27
 */
@RestController
@RequestMapping("/idempotent")
public class IdempotentController {
    @Autowired
    UserService userService;

    @ApiIdempotent //幂等注解
    @RequestMapping("/save")
    public ServerResponse save(){
        return userService.save();
    }
}
