package cn.linstudy.travel.config;

import cn.linstudy.travel.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/downloadCert")
                .excludePathPatterns("/images/*")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/register.html")
                .excludePathPatterns("/admin/list")
                .excludePathPatterns("/strategyTheme/list")
                .excludePathPatterns("/strategyTheme/saveOrUpdate")
                .excludePathPatterns("/strategyTheme/delete")
                .excludePathPatterns("/strategyCatalog/list")
                .excludePathPatterns("/strategy/list")
                .excludePathPatterns("/travel/list")
                .excludePathPatterns("/strategyCatalog/saveOrUpdate")
                .excludePathPatterns("/strategyCatalog/delete")
                .excludePathPatterns("/strategy/input")
                .excludePathPatterns("/strategy/info")
                .excludePathPatterns("/strategy/saveOrUpdate")
                .excludePathPatterns("/strategy/delete")
                .excludePathPatterns("/strategy/changeState")
                .excludePathPatterns("/travel/getContentById")
                .excludePathPatterns("/travel/audit")
                .excludePathPatterns("/user/list")
                .excludePathPatterns("/order/list")
                .excludePathPatterns("/hotel/list")
                .excludePathPatterns("/user/delete")
                .excludePathPatterns("/user/audit")
                .excludePathPatterns("/hotel/input")
                .excludePathPatterns("/hotel/delete")
                .excludePathPatterns("/hotel/edit")
                .excludePathPatterns("/room/list")
                .excludePathPatterns("/room/roomAdd")
                .excludePathPatterns("/room/roomEdit")
                .excludePathPatterns("/room/delete")
                ;

    }
}
