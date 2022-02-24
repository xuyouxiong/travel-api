package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.qo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@Api(tags = "后端常用接口")
@RequestMapping("/common")
public class CommonController {
    @PassLogin
    @ApiOperation(value = "上传图片的接口")
    @PostMapping("uploadFile")
    @ResponseBody
    public JsonResult uploadFile(MultipartFile file){
        try {
            String originName = file.getOriginalFilename();
            String name = createImageName();
//            String path = "d:/website_travel/travel-api/travel-mgrsite/target/classes/static/images/";
            String path = "/Users/sunmaoyun/duncan/bishe/xuhaining/spring-boot_-travel/travel-mgrsite/target/classes/static/";
            uploadFile(file.getBytes(), path, name + originName.substring(originName.lastIndexOf("."), originName.length()));
            // 存储的目录
            return new JsonResult(200, "/images/" + name + originName.substring(originName.lastIndexOf("."), originName.length()) );
        }catch (Exception e) {
            return new JsonResult(500, "上传文件出错");
        }
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String createImageName() {
        String s = "xuhaining";
        Random rand = new Random();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

        String time = df.format(new Date());
        int randnum1 = rand.nextInt(900)+100;
        time = time.concat(String.valueOf(randnum1));
        s += time;
        return s;
    }
}
