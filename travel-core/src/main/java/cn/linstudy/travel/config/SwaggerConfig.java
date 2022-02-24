package cn.linstudy.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Swagger配置类
 * 
 * @Date 2021/4/9 17:22
 */
@Configuration//表示这是一个配置类
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket reeateDocket(){
    List<Parameter> parameterList=new ArrayList<>();
    ParameterBuilder parameterBuilder=new ParameterBuilder();
    parameterBuilder.name("token")
        .description("swagger调试用，模拟传入用户凭证")
        .modelRef(new ModelRef("String"))
        .parameterType("header").required(false);
    parameterList.add(parameterBuilder.build());
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())//创建该Api的基本信息（这些基本信息会展现在文档页面中）
        .select()//函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger ui来展现
        .apis(RequestHandlerSelectors.basePackage("cn.linstudy"))//指定需要扫描的包路路径
        .paths(PathSelectors.any())
        .build()
        .globalOperationParameters(parameterList)
        ;
  }
  //配置swagger的信息
  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
        .title("SpringBoot-Travel项目实战")
        .description("接口")
        .termsOfServiceUrl("")
        .version("1.0")
        .build();
  }
}
