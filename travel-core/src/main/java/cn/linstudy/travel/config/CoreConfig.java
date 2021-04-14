package cn.linstudy.travel.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置类
 * @Author XiaoLin
 * @Date 2021/4/9 14:51
 */
@Configuration
@MapperScan(basePackages = "cn.linstudy.travel.mapper")
public class CoreConfig {

  /**
   * MyBatis-Plus分页插件配置
   * @return
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {

    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

    paginationInnerInterceptor.setOverflow(true); //合理化

    interceptor.addInnerInterceptor(paginationInnerInterceptor);

    return interceptor;

  }
}
