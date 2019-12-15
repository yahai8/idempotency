package com.cn.service;

import com.cn.common.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 19:20
 */
public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);
}
