package cn.linstudy.travel.redis.service;

/**
 * @Description 用户缓存的业务类
 * @Author XiaoLin
 * @Date 2021/4/10 9:13
 */
public interface UserInfoRedisService {

  /**
   * 将验证码设置到redis中
   * @param phone
   * @param code
   */
  void setVerifyCode(String phone, String code);

  /**
      * @Description: 将登录的Token放入Redis，时间为30分钟
      * @author XiaoLin
      * @date 2021/4/11
      * @Param: [key, token]
      * @return void
      */
  void setLoginToekn(String key, String token);

  /**
      * @Description: 根据key拿到value
      * @author XiaoLin
      * @date 2021/4/11
      * @Param: [key]
      * @return java.lang.String
      */
  String getValue(String key);

  void resetTime(String key);
}
