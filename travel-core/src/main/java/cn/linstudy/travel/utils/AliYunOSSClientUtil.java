package cn.linstudy.travel.utils;

import cn.linstudy.travel.constant.OSSClientConstants;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.LifecycleRule;
import com.aliyun.oss.model.LifecycleRule.RuleStatus;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.SetBucketLifecycleRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class AliYunOSSClientUtil {

  private static String ENDPOINT;

  private static String ACCESS_KEY_ID;

  private static String ACCESS_KEY_SECRET;

  private static String BACKET_NAME;

  private static String FOLDER;

  private static String Key = "key";

  //初始化属性
  static {
    ENDPOINT = OSSClientConstants.ENDPOINT;
    ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
    ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
    BACKET_NAME = OSSClientConstants.BACKET_NAME;
    FOLDER = OSSClientConstants.FOLDER;
  }

  /**
   * @param @param  file
   * @param @return
   * @param @throws Exception 文件
   * @return Map<String, String>
   * @Title: update
   * @description 返回 格式的文件访问路径  和文件类型
   */
  public static synchronized String uploadImg(MultipartFile file) throws Exception {

      //生成文件名称
      String uuid = UUID.randomUUID().toString();
      String orgFileName = file.getOriginalFilename();//获取真实文件名称 xxx.jpg
      String ext = "." + FilenameUtils.getExtension(orgFileName);//获取拓展名字.jpg
      String fileName = uuid + ext;//xxxxxsfsasa.jpg
      // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，
      // 创建OSSClient实例。
      OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    //创建上传Object的Metadata
    ObjectMetadata metadata = new ObjectMetadata();
    //指定该Object被下载时的网页的缓存行为
    metadata.setCacheControl("no-cache");
    //指定该Object下设置Header
    metadata.setHeader("Pragma", "no-cache");
    //指定该Object被下载时的内容编码格式
    metadata.setContentEncoding("utf-8");
    //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
    //如果没有扩展名则填默认值application/octet-stream
      // 上传文件流。
    ossClient.putObject(BACKET_NAME, FOLDER + fileName, file.getInputStream(),metadata);
    URL url = ossClient.generatePresignedUrl(BACKET_NAME, FOLDER,new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10));
      // 关闭OSSClient。
      ossClient.shutdown();
      return url.toString();
    }



}
