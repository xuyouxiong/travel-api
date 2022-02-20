package cn.linstudy.travel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * 
 * @Date 2021/4/9 14:47
 */
@SpringBootApplication
@EnableSwagger2//开启Swagger2
@EnableScheduling
public class WebSiteApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebSiteApiApplication.class,args);
  }
}
