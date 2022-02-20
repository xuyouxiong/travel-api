package cn.linstudy.travel.config;

import cn.linstudy.travel.interceptor.JwtInterceptor;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 配置拦截器
 * 
 * @Date 2021/4/11 14:02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public UserInfoArgumentResolver getInstance(){
    return new UserInfoArgumentResolver();
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
      resolvers.add(getInstance());
  }

  // 将jwtInterceptor拦截器的实例对象放入Bean容器
  @Bean
  public JwtInterceptor jwtInterceptor(){
    return new JwtInterceptor();
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/users/login")
        .excludePathPatterns("/users/register")
        .excludePathPatterns("/users/sendVerifyCode")
        .excludePathPatterns("/users/checkPhone")
        .excludePathPatterns("/swagger-resources/**")
        .excludePathPatterns("/v2/api-docs/**")
        .excludePathPatterns("/swagger-resources/configuration/security/**")
        .excludePathPatterns("/webjars/**")
        .excludePathPatterns("/swagger-ui.html")
        .excludePathPatterns("/error")
        .excludePathPatterns("/swagger-resources/configuration/ui/**");

  }
}
