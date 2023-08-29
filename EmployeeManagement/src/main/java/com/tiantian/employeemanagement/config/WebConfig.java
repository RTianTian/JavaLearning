package com.tiantian.employeemanagement.config;

import com.tiantian.employeemanagement.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 天天
 * @date 2023/8/29 23:20
 * @description
 */
@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 加载登录拦截器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/user/login", "/user/register"
//                );
//    }

    /**
     * 前后端分离的跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许跨域的url
                .addMapping("/**")
                //设置放行哪些原始域，SpringBoot2.4.4下低版本使用allowedOrigins("*")
                .allowedOriginPatterns("*")
                //是否允许Cookie
                .allowCredentials(true)
                //设置 header 能携带的信息
                .allowedHeaders("*")
                //支持跨域的请求方式，括号填*放行全部
                .allowedMethods("*")
                //设置跨域过期时间，单位为秒
                .maxAge(3600);
    }
}
