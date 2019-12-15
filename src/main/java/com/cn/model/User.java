package com.cn.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 17:22
 */
@Data
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name ="sex")
    private String sex;
}
