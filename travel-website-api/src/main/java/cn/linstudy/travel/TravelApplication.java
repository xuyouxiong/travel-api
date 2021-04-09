package cn.linstudy.travel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/9 14:47
 */
@SpringBootApplication
@EnableSwagger2//开启Swagger2
public class TravelApplication {
  public static void main(String[] args) {
    SpringApplication.run(TravelApplication.class,args);
  }
}
