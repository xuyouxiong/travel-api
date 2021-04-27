package cn.linstudy.travel.redis;

import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.utils.DateUtil;
import java.util.Date;
import lombok.Getter;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 20:03
 */
@Getter
public enum RedisKeyEnum {

  // 访客数量相关
  VISITOR_NUM_TODAY("visitor_num_today",getBetweenEndDate()),

  // 用户收藏相关
  COLLECTION_STRATEGY("collection_strategy",-1l),

  // 用户点赞相关，并且用工具类将超时时间设置为今天最后一天
  STRATEGY_THUMBUP("strategy_thumbup",-1L),

  // 用户收藏相关
  STRATEGY_FAVOR("strategy_favor",-1L),

  // 攻略统计相关key实例对象
  STRATEGY_STATISTICS_VO("strategy_statis_vo",-1L),

  // 用户注册验证码 key 实例对象
  ENUM_VERYFY_CODE("veryfy_code",SystemConstant.VERIFY_CODE_VAI_TIME*60L),

  // 用户登录Toekn
  ENUM_LOGIN_TOKEN("login_token",SystemConstant.USER_INFO_TOKEN_VAI_TIME*60L);


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

  public static Long getBetweenEndDate(){
    Date endDate = DateUtil.getEndDate(new Date());
    return DateUtil.getDateBetween(new Date(), endDate);
  }

}
