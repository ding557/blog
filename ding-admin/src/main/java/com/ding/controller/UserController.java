package com.ding.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ding.entity.user.User;
import com.ding.mapper.UserMapper;
import com.ding.util.Result;
import com.ding.util.SMSUtils;
import com.ding.util.TokenUtils;
import com.ding.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName 丁557
 * @DATE 2022/3/22 20:36
 * @Auther 73660
 * @Description 用户控制
 * @Version 1.0
 */

//@CrossOrigin   //跨域访问
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;
    //走自定义配置文件，实现序列化存储(不乱码)
    @Resource
    @Qualifier("redisTemplate")
    private RedisTemplate<String,String> redisTemplate;

    //登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user ) throws Exception {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,user.getName()).eq(User::getPassword,user.getPassword()));
        if(res == null){
            return Result.error("-1","用户名或密码错误");
        }

        // 生成token
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        return  Result.success(res);
    }
    //注册
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,user.getName()));
        if(res != null){
            return Result.error("-1","用户名重复");
            
        }
        String code = redisTemplate.opsForValue().get(user.getPhone());
        if (!code.equals(user.getVerification())){
            return Result.error("-1","验证码无效，请重新获取");
        }
        userMapper.insert(user);
        return  Result.success();
    }
    //短信验证码

    @GetMapping("/Sms")
    public Result<?> aliyunSms(@RequestParam("phone") String phone){

        //查询当前手机号在Redis是否存在
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return  Result.error("-1","已存在，还未超时");
        }
        //生成验证码
       Integer param = ValidateCodeUtils.generateValidateCode(6);
        //将生成的验证码存储到redis中
        try {
           SMSUtils.sendShortMessage(phone,param);
        }catch (Exception e){
            return  Result.error("-1","发送失败");
        }
        //300秒有效期(5分钟)
        redisTemplate.opsForValue().set(phone,String.valueOf(param),300, TimeUnit.SECONDS);
        return Result.success();
    }
    //新增
    @PostMapping
    public Result<?> Save(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
    //修改
    @PutMapping
    public Result<?> Update(@RequestBody User user){

        userMapper.updateById(user);
        return Result.success();
    }
    //修改
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable Long id){

        userMapper.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
       if(!StrUtil.isNotBlank(search)){   //不是空
           wrapper.like(User::getName,search);
       }
        Page<User> page= userMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<User>lambdaQuery().like(User::getName, search));
        return Result.success(page);
    }



}
