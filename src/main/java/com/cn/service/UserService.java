package com.cn.service;

import com.cn.common.response.ResponseCode;
import com.cn.common.response.ServerResponse;
import com.cn.dao.UserRepository;
import com.cn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 17:28
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ServerResponse save(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("张三");
        user.setSex("男");
        User save = userRepository.save(user);
        if (save ==null){
            return ServerResponse.success("操作失败");
        }
        return ServerResponse.success("操作成功");
    }
}
