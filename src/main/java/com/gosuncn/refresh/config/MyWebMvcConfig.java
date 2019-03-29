package com.gosuncn.refresh.config;

import com.gosuncn.refresh.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: chenxihua
 * @Date: 2019/3/28:10:16
 * @Version 1.0
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/verify/displayList").setViewName("login");
//            }


            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/static/js/**");
            }
        };
        return webMvcConfigurer;
    }

}
