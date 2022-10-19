package com.ding.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName 丁557
 * @DATE 2022/3/22 20:38
 * @Auther 73660
 * @Description
 * @Version 1.0
 */
@TableName("user")
@Data
@NoArgsConstructor  //空参构造方法
@AllArgsConstructor //全参构造方法
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
    private String Phone;
    private String photo;
    private Integer role;
    @TableField(exist = false)
    private String token;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private String Verification;


}
