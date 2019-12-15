package com.cn.controller;

import com.cn.annotation.ApiIdempotent;
import com.cn.common.response.ServerResponse;
import com.cn.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 21:08
 */
@RestController
@RequestMapping("/")
public class GenerateTokenController {
    @Autowired
    TokenService tokenService;

    /**
     * 生成token
     * @return
     */
    @GetMapping("/token")
    public ServerResponse token() {
        return tokenService.createToken();
    }
}
