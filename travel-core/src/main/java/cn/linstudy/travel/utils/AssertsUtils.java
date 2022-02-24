package cn.linstudy.travel.utils;

import cn.linstudy.travel.exception.LogicException;
import org.springframework.util.StringUtils;

/**
 * @Description 断言工具类
 *
 * @Date 2021/4/10 19:24
 */
public class AssertsUtils {

  private AssertsUtils() {
  }

  /**
   * @return void
   * @Description: 判断指定的参数是否为null，或者空串，如果为空抛出异常
   *
   * @date 2021/4/10
   * @Param: [text, message]
   */
  public static void hasText(String text, String message) {
    if (!StringUtils.hasText(text)) {
      throw new LogicException(message);
    }
  }

  /**
      * @Description: 判断传入的参数是否相等
      *
      * @date 2021/4/10
      * @Param: [param1, param2, message]
      * @return void
      */
  public static void isEquals(String param1, String param2, String message) {
    if (param1 == null || param2 == null) {
      throw new LogicException("传入的参数为空");
    }
    if (!param1.equals(param2)) {
      throw new LogicException(message);
    }
  }


}
