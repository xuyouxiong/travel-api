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

}
