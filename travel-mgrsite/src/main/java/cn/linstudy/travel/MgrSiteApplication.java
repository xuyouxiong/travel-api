package cn.linstudy.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/13 9:47
 */
@SpringBootApplication
@EnableSwagger2
public class MgrSiteApplication {
  public static void main(String[] args) {
      SpringApplication.run(MgrSiteApplication.class,args);
  }
}
