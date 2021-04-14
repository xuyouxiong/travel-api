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

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 21:21
 */
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
      //这里是token解析失败
      throw new LogicException("Token解析失败");
    }
    return audience;
  }

  public static void main(String[] args) {
    HashMap<String, String> map = new HashMap<>();
    map.put("phone","13700000001");
    System.out.println(getToken("20", map));
  }
}
