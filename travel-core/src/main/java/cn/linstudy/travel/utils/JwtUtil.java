package cn.linstudy.travel.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 21:21
 */
public class  JwtUtil {
  private static String TOKEN = "XiaoLin";// 私钥
  /**
   * 生成token
   * @param map  //传入payload
   * @return 返回token
   */
  public static String getToken(Map<String,String> map){
    JWTCreator.Builder builder = JWT.create();
    map.forEach((k,v)->{
      builder.withClaim(k,v);
    });
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND,7);
    builder.withExpiresAt(instance.getTime());
    return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
  }
  /**
   * 验证token
   * @param token
   * @return
   */
  public static void verify(String token){
    JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
  }
  /**
   * 获取token中payload
   * @param token
   * @return
   */
  public static DecodedJWT getToken(String token){
    return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
  }

}
