package cn.linstudy.travel.redis;

import cn.linstudy.travel.constant.SystemConstant;
import lombok.Getter;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 20:03
 */
@Getter
public enum RedisKeyEnum {

  // 用户注册验证码 key 实例对象
  ENUM_VERYFY_CODE("veryfy_code",SystemConstant.VERIFY_CODE_VAI_TIME*60L);


  // 前缀
  private String prefix;
  // 有效时长
  private Long time;

  RedisKeyEnum(String prefix, long time) {
    this.prefix = prefix;
    this.time = time;
  }

  // 拼接key
  public String join(String... values){
    StringBuilder sb = new StringBuilder();
    sb.append(this.prefix);
    for (String value : values) {
      sb.append(":").append(value);
    }
    return sb.toString();
  }
}
