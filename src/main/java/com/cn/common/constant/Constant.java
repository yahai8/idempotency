package com.cn.common.constant;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 19:42
 */

/**
 * 常量类
 */
public class Constant {
    public interface Redis {
        Integer EXPIRE_TIME_MINUTE = 60*3;// 过期时间, 60s, 一分钟
        String TOKEN_PREFIX = "token:";  //token值前缀
    }
}