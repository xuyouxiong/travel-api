package cn.linstudy.travel.config;

import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description 自定义对象参数解析器
 * 
 * @Date 2021/4/19 15:19
 */

public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
  UserInfoRedisService userInfoRedisService;

  // 表示解析对象的类型是什么类型
  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterType()== UserInfo.class && methodParameter.hasParameterAnnotation(
        UserParam.class);
  }

  // 如果上面的结果返回为true才执行这一步
  @Override
  public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
    String token = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getHeader("token");
    return userInfoRedisService.getUserInfoByToken(token);
  }
}
