package cn.linstudy.travel.annotation;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 如果接口方法声明的UserInfo类型参数，并且贴了这个注解， 表示使用自定义参数解析器进行参数解析
 * @Author XiaoLin
 * @Date 2021/4/20 14:02
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserParam {

}
