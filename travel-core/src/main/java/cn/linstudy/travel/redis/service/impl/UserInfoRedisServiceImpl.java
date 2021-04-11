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

}
