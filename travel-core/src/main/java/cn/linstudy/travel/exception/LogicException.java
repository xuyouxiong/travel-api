package cn.linstudy.travel.exception;

/**
 * @Description 自定义异常
 * @Author XiaoLin
 * @Date 2021/4/10 10:34
 */
public class LogicException extends RuntimeException{

  // 标记非系统异常
  public LogicException(String message) {
    super(message);
  }
}
