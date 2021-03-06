package cn.linstudy.travel.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 解决跨域配置类
 * 
 * @Date 2021/4/9 20:31
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

  }

  @Bean
  public WebConfigurer corsConfigurer() {
    return new WebConfigurer() {
      @Override
      //重写父类提供的跨域请求处理的接口
      public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
            //放行哪些原始域
            .allowedOriginPatterns("*")
            //是否发送Cookie信息
            .allowCredentials(true)
            //放行哪些原始域(请求方式)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            //放行哪些原始域(头部信息)
            .allowedHeaders("*")
            //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            .exposedHeaders("Header1", "Header2");
      }

    };

  }
}
