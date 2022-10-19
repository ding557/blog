package com.ding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ding.entity.user.User;

public interface UserMapper extends BaseMapper<User> {
    // 一对多查询
//    Page<User> findPage(Page<User> page, @Param("nickName") String nickName);

//    @Select("select count(id) count, address from user GROUP BY address")
//    List<UserAddressDto> countAddress();
//
//    // 查询用户名
//    @Select("select password from user where username=#{username}")
//    User selectByName(String username);
//
//    @Update("update user set password = #{newPass} where id = #{userId}")
//    int updatePass(Map<String, Object> map);
}
