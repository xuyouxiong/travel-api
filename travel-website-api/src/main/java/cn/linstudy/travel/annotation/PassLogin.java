package cn.linstudy.travel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * @Description 登录拦截注解，如果请求方法贴有该注解，表示该方法需要进行登录检查
 * 
 * @Date 2021/4/11 15:01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassLogin {

}
