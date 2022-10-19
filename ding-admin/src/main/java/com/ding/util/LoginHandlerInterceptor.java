package com.ding.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.ding.entity.user.User;
import com.ding.exception.CustomException;
import com.ding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandlerInterceptor
 * @Description TODO
 * @Author Ding557
 * @Date 2022/7/21 20:13
 * @Version 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;
    @Resource
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



//            String token = request.getHeader("token");
//            if (StrUtil.isBlank(token)) {
//                throw new CustomException("401", "未获取到token, 请重新登录");
//            }
//            Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
//            User user = userMapper.selectById(userId);
//            if (user == null) {
//                throw new CustomException("401", "token不合法");
//            }
//            // 验证 token
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//            try {
//                jwtVerifier.verify(token);
//            } catch (Exception e) {
//                throw new CustomException("401", "token不合法");
//            }
        //默认访问一次请求都会对该请求进行计数+1
        redisTemplate.opsForValue().increment("login",1);

        return true;

    }
}
