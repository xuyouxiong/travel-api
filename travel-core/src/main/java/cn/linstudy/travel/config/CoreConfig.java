package cn.linstudy.travel.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @Description 配置类
 * @Author XiaoLin
 * @Date 2021/4/9 14:51
 */
@Configuration
@MapperScan(basePackages = "cn.linstudy.travel.mapper")
public class CoreConfig {



  //mongodb 去除_class属性
  @Bean
  public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory,
      MongoMappingContext context, BeanFactory beanFactory) {
    DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
    MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
    try {
      mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
    } catch (NoSuchBeanDefinitionException ignore) {
    }
    // Don't save _class to mongo
    mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return mappingConverter;
  }

  /**
   * MyBatis-Plus分页插件配置
   *
   * @return
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {

    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(
        DbType.MYSQL);
    paginationInnerInterceptor.setOverflow(true); //合理化
    interceptor.addInnerInterceptor(paginationInnerInterceptor);
    return interceptor;

  }
}
