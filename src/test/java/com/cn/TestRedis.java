package com.cn;

import com.cn.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 19:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    JedisUtil jedisUtil;

    @Test
    public void test(){
//        jedisUtil.set("12","12",180);
        jedisUtil.exists("token");
    }
}
