package cn.linstudy.travel.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:application.yml")
@Component
public class SendMessageUtils {

  private static String accessKeyId;  // TODO 修改成自己的
  private static String accessKeySecret;   // TODO 修改成自己的

  @Value("${aliyun.accessKeyId}")
  public  void setAccessKeyId(String accessKeyId) {
    SendMessageUtils.accessKeyId = accessKeyId;
  }

  @Value("${aliyun.secret}")
  public  void setAccessKeySecret(String secret) {
    SendMessageUtils.accessKeySecret = secret;
  }


  //产品名称:云通信短信API产品,开发者无需替换
  static final String product = "Dysmsapi";
  //产品域名,开发者无需替换
  static final String domain = "dysmsapi.aliyuncs.com";


  public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {
    //可自助调整超时时间
    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    //初始化acsClient,暂不支持region化
    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
    IAcsClient acsClient = new DefaultAcsClient(profile);
    //组装请求对象-具体描述见控制台-文档部分内容
    SendSmsRequest request = new SendSmsRequest();
    //必填:待发送手机号
    request.setPhoneNumbers(telephone);
    //必填:短信签名-可在短信控制台中找到
    request.setSignName("XiaoLin");    // TODO 修改成自己的
    //必填:短信模板-可在短信控制台中找到
    request.setTemplateCode("SMS_213078152");    // TODO 修改成自己的
    //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
    request.setTemplateParam("{\"code\":\"" + code + "\"}");
    //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
    //request.setSmsUpExtendCode("90997");
    //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//    request.setOutId("yourOutId");
    //hint 此处可能会抛出异常，注意catch
    SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
      System.out.println("短信发送成功！");
    } else {
      System.out.println("短信发送失败！");
    }
    return sendSmsResponse;
  }

}