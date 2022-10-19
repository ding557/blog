package com.ding.util;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsProperties
 * @Description 阿里云短信接口调用SDK
 * @Author Ding557
 * @Date 2022/4/19 19:58
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "ding")
//@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class SmsProperties implements InitializingBean{
    @Value("${ding.accessKeyId}")
    private String accessKeyId;
    @Value("${ding.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ding.templateCode}")
    private String templateCode;
    @Value("${ding.signName}")
    private String signName;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String TEMPLATE_CODE;
    public static String SIGN_NAME;
    //当私有成员被赋值后，此方法自动被调用，从而初始化常量


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        TEMPLATE_CODE = templateCode;
        SIGN_NAME = signName;
    }

}
