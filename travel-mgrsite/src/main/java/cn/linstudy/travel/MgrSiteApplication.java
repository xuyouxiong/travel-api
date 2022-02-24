package cn.linstudy.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * 
 * @Date 2021/4/13 9:47
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class MgrSiteApplication {
  public static void main(String[] args) {
      SpringApplication.run(MgrSiteApplication.class,args);
  }
}
