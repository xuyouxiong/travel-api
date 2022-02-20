package cn.linstudy.travel.utils;

import cn.linstudy.travel.exception.LogicException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class  JwtUtil {
  private static String TOKEN = "XiaoLin";// 私钥

  /**
      * @Description: 生成token
      * @Param: [id 用户id, map 传入的荷载]
      * @return java.lang.String
      */
  public static String getToken(String id,Map<String,String> map){
    JWTCreator.Builder builder = JWT.create();
    map.forEach((k,v)->{
      builder.withClaim(k,v);
    });
    Calendar instance = Calendar.getInstance();
    // 设置有效期为30分钟
    instance.add(Calendar.MINUTE,30);
    builder.withExpiresAt(instance.getTime())
            // 设置签发对象，用户id
            .withAudience(id)
            // 设置签发时间
            .withIssuedAt(new Date());
    return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
  }
  /**
   * 验证token
   * @param token
   * @return
   */
  public static void verify(String token,String id){
    try {
    JWT.require(Algorithm.HMAC256(id+TOKEN)).build().verify(token);
    }catch (Exception e){
      throw new LogicException("Token校验失败");
    }
  }
  /**
   * 获取token中payload
   * @param token
   * @return
   */
  public static DecodedJWT getToken(String token){
    return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
  }

  /**
   * 获取签发对象
   */
  public static String getAudience(String token) {
    String audience = null;
    try {
      audience = JWT.decode(token).getAudience().get(0);
    } catch (JWTDecodeException j) {

      System.out.println("检验是否登录");
      //这里是token解析失败
      throw new LogicException("未登录");
    }
    return audience;
  }

  public static void main(String[] args) {
    System.out.println(getAudience(
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicGhvbmUiOiIxMzcwMDAwMDAwMCIsImV4cCI6MTYxODgxODE0NCwiaWF0IjoxNjE4ODE2MzQ0fQ.uawP4x8MpbguQFufn0iYRArgonddOAVJMpZnj1DaGQE"));
  }
}
