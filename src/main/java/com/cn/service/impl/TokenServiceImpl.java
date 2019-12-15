package com.cn.service.impl;

import com.cn.common.constant.Constant;
import com.cn.common.response.ResponseCode;
import com.cn.common.response.ServerResponse;
import com.cn.exception.ServiceException;
import com.cn.service.TokenService;
import com.cn.util.JedisUtil;
import com.cn.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 19:20
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static String TOKEN_NAME = "token";
    @Autowired
    JedisUtil jedisUtil;

    /**
     * 生成token
     * @return
     */
    @Override
    public ServerResponse createToken() {
        String uuid = UuidUtil.getUuid();
        StringBuilder token = new StringBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(uuid);
        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);
        return ServerResponse.success(token.toString());
    }

    /**
     * 校验token
     * @param request
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        //从request的头部中获取名为token的header
        String token = request.getHeader(TOKEN_NAME);
        //判断此token是否为空
        if (StringUtils.isEmpty(token)) {
            //如果为空，就检查请求参数上是否有名为token，并取出值赋给token
            token = request.getParameter(TOKEN_NAME);
            //如果此时还为空，就属于非法操作，直接抛出异常
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        //到这一步就代表本次请求确实携带了token，这时候还需要校验token是否配对，可能别人伪造token
        if (!jedisUtil.exists(token)) {
            //如果redis查询没有此token那么此token就是假的，别人伪造的，抛出异常
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        //删除redis中的token
        Long del = jedisUtil.del(token);
        //必须要对删除结果进行校验，否则还是会出现并发问题
        if (del < 0) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
