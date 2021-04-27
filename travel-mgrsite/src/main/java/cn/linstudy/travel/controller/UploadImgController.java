package cn.linstudy.travel.controller;

import cn.linstudy.travel.constant.OSSClientConstants;
import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.utils.AliYunOSSClientUtil;
import com.aliyun.oss.OSSClient;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/15 14:25
 */
@Controller
public class UploadImgController {

  @RequestMapping("/uploadImg")
  @ResponseBody
  public Object uploadImg(MultipartFile pic) throws Exception {
    //思考： 图片存哪？-- 上传到OSS
    String path = AliYunOSSClientUtil.uploadImg(pic);
    return path;
  }
}
