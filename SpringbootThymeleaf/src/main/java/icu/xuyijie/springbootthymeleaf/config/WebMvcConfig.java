package icu.xuyijie.springbootthymeleaf.config;

import icu.xuyijie.springbootthymeleaf.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 徐一杰
 * @date 2023/7/22
 * @description
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/user/login", "/user/register", "index.html", "/static/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //这个不加也行，因为默认就是/访问index.html
        registry.addViewController("/").setViewName("index");
    }

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
