package com.cn.interceptor;

import com.cn.annotation.ApiIdempotent;
import com.cn.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 19:18
 */

/**
 * 实现幂等得拦截器
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断handler是否是handlerMethod的实例，不是就放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //得到执行方法
        Method method = handlerMethod.getMethod();
        //得到执行方法上是否有幂等性的注解
        ApiIdempotent annotation = method.getAnnotation(ApiIdempotent.class);
        //如果不为空，就检查token，为空就说明此方法不需要进行幂等操作，直接放行
        if (annotation != null) {
            //校验token
            tokenService.checkToken(request);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
