package com.ding.util.config;

import com.ding.util.TokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @ClassName SecurityConfig
 * @Description SecurityConfig
 * @Author Ding557
 * @Date 2022/7/24 19:28
 * @Version 1.0
 **/
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //授权请求
                .antMatchers("/**").permitAll() // '/'地址所有人可以访问
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }

    /*
     * 注入BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
