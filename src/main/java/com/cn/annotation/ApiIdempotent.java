package com.cn.annotation;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 19:15
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类，在需要保证接口幂等性 的controller的方法上加上此注解
 */
@Target({ElementType.METHOD})//目标作用于方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
