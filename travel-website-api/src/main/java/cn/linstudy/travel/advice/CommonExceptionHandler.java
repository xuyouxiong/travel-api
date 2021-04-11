package cn.linstudy.travel.advice;

import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.qo.response.JsonResult;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 通用异常处理类
 *  ControllerAdvice  controller类功能增强注解, 动态代理controller类实现一些额外功能
 *  请求进入controller映射方法之前做功能增强: 经典用法:日期格式化
 *  请求进入controller映射方法之后做功能增强: 经典用法:统一异常处理
 * @Author XiaoLin
 * @Date 2021/4/10 19:17
 */
public class CommonExceptionHandler {

  //这个方法定义的跟映射方法操作一样
  @ExceptionHandler(LogicException.class)
  @ResponseBody
  public Object LogicException(Exception e, HttpServletResponse resp) {
    e.printStackTrace();
    resp.setContentType("application/json;charset=utf-8");
    return JsonResult.error(SystemConstant.CODE_ERROR_PARAM, e.getMessage(), null);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public Object  RuntimeException(Exception e, HttpServletResponse resp) {
    e.printStackTrace();
    resp.setContentType("application/json;charset=utf-8");
    return JsonResult.defaultError();
  }
}
