package cn.linstudy.travel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置类
 * @Author XiaoLin
 * @Date 2021/4/9 14:51
 */
@Configuration
@MapperScan(basePackages = "cn.linstudy.travel.mapper")
public class CoreConfig {

}
