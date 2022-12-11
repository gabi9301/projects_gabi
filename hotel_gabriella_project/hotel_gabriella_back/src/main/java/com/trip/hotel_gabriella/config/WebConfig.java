package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.common.security.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
//@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//    private final JwtInterceptor jwtInterceptor;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor).addPathPatterns("/member/*").addPathPatterns("/admin/*");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST");
    }
}
