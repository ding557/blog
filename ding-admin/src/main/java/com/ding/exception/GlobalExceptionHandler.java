package com.ding.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.ding.controller.UserController;
import com.ding.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName 丁557
 * @DATE 2022/3/22 20:36
 * @Auther 73660
 * @Description Handler异常
 * @Version 1.0
 */

@ControllerAdvice(basePackages = "com.ding.controller.UserController")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json串
    public Result<?> customer(HttpServletRequest request, CustomException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result<?> error(HttpServletRequest request, Exception e) {
        log.error("异常信息：", e);
        return Result.error("-1", "系统异常");
    }


}
