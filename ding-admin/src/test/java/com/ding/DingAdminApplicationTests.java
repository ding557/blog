package com.ding;

import com.aliyun.teaopenapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DingAdminApplicationTests {


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

        public static void main(String[] args) throws Exception {

            //查看服务器是否运行，打出pong表示OK
            //System.out.println(propdderties.getAccessKeyId());

//            java.util.List<String> args = java.util.Arrays.asList(args_);
//            com.aliyun.dysmsapi20170525.Client client = DingAdminApplicationTests.createClient("LTAI5t8Vn4TDT8eJG1ToJF9G", "YIDoaJz0ddlzWFT7eZkqCrV37wAVUV");
//            SendSmsRequest sendSmsRequest = new SendSmsRequest()
//            // 复制代码运行请自行打印 API 的返回值
//            .setSignName("ding557") //签名
//                    .setTemplateCode("SMS_238981446")//模板
//                    .setPhoneNumbers("15669215815");//手机号
//            //构建短信验证码
//            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//            objectObjectHashMap.put("name",2233);
//            sendSmsRequest.setTemplateParam(JSONObject.toJSONString(objectObjectHashMap));
//            // 复制代码运行请自行打印 API 的返回值
//            client.sendSms(sendSmsRequest);
        }


}
