package com.ding.util;

import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassName SMSUtils
 * @Description 短信发送工具类
 * @Author Ding557
 * @Date 2022/4/21 19:41
 * @Version 1.0
 **/
public class SMSUtils {
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    public static void sendShortMessage(String phoneNumbers,Integer param) throws Exception {

        com.aliyun.dysmsapi20170525.Client client = SMSUtils.createClient( SmsProperties.ACCESS_KEY_ID,SmsProperties.ACCESS_KEY_SECRET);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(SmsProperties.SIGN_NAME) //签名
                .setTemplateCode(SmsProperties.TEMPLATE_CODE)//模板
                .setPhoneNumbers(phoneNumbers) //电话号
                .setTemplateParam("{\"name\":\""+param+"\"}");//


            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(sendSmsRequest);

    }
}
