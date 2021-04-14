package cn.linstudy.travel.redis.service.impl;

import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import cn.linstudy.travel.service.UserInfoService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 9:18
 */
@Service
public class UserInfoRedisServiceImpl implements UserInfoRedisService {

  @Autowired
  private StringRedisTemplate template;
  @Override
  public void setVerifyCode(String phone, String code) {
    String  key = RedisKeyEnum.ENUM_VERYFY_CODE.join(phone);
    String value = code;
    // 将验证码放入Redis，并且设置时效
    template.opsForValue().set(key,value, RedisKeyEnum.ENUM_VERYFY_CODE.getTime(), TimeUnit.SECONDS);
  }

  /**
      * @Description: 将登录Token放进Redis
      * @author XiaoLin
      * @date 2021/4/11
      * @Param: [key, token]
      * @return void
      */
  @Override
  public void setLoginToekn(String key, String token) {
    template.opsForValue().set(key,token,RedisKeyEnum.ENUM_LOGIN_TOKEN.getTime(),TimeUnit.SECONDS);
  }

  /**
      * @Description: 通过key获取value
      * @author XiaoLin
      * @date 2021/4/11
      * @Param: [key]
      * @return java.lang.String
      */
  @Override
  public String getValue(String key) {
    return template.opsForValue().get(key);
  }

  /**
      * @Description: 根据key重新设置时间
      * @author XiaoLin
      * @date 2021/4/11
      * @Param: [key]
      * @return void
      */
  @Override
  public void resetTime(String key) {
    template.expire(key,SystemConstant.USER_INFO_TOKEN_VAI_TIME*60L,TimeUnit.SECONDS);
  }

}
