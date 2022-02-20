package cn.linstudy.travel.constant;

import cn.linstudy.travel.utils.SendMessageUtils;
import java.net.URL;
import lombok.Data;
import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * @Description
 * 
 * @Date 2021/4/15 14:26
 */
@PropertySource(value = "classpath:application.yml")
@Component
public class OSSClientConstants {

  /**
   * 阿里云API的外网域名
   */
    public static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";

  /**
   * 阿里云API的密钥Access Key ID
   */
  public static  String ACCESS_KEY_ID;
  /**
   *阿里云API的密钥Access Key Secret
   */
  public static String ACCESS_KEY_SECRET;

  /**
   * 阿里云API的bucket名称
   * 在阿里云上自己创建一个bucket
   */
  public static final String BACKET_NAME = "linstudy-test";

  /**
   * 阿里云API的文件夹名称
   * 在阿里云上自己创建一个文件夹，方便分类管理图片
   */
  public static final String FOLDER="image/";

  @Value("${aliyun.accessKeyId}")
  public  void setAccessKeyId(String accessKeyId) {
    this.ACCESS_KEY_ID = accessKeyId;
  }

  @Value("${aliyun.secret}")
  public  void setAccessKeySecret(String secret) {
    this.ACCESS_KEY_SECRET = secret;
  }
}
