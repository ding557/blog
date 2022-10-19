package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
@EnableWebSecurity   //开启security
public class DingAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingAdminApplication.class, args);
    }


}

