package com.cn.dao;

import com.cn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 17:25
 */
public interface UserRepository extends JpaRepository<User,String> {
}
