package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.qo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "前端目的地相关接口")
@RequestMapping("/common")
public class CommonController {
    @PassLogin
    @ApiOperation(value = "根据id查询攻略细节")
    @PostMapping("uploadFile")
    @ResponseBody
    public JsonResult uploadFile(MultipartFile pic){
        try {
            String originName = pic.getOriginalFilename();
            String name = createImageName();
            String path = "/Users/sunmaoyun/Sites/localhost/travel-website/upload/";
            uploadFile(pic.getBytes(), path, name + originName.substring(originName.lastIndexOf("."), originName.length()));
            // 存储的目录
            System.out.println("-----------> 获取到file文件 <-----------");
            System.out.println(pic.getOriginalFilename());
            return new JsonResult(200, "/upload/" + name + originName.substring(originName.lastIndexOf("."), originName.length()) );
        }catch (Exception e) {
            System.out.println("-----------> 获取到file文件 <-----------");
            System.out.println("上传图片出错");
            return new JsonResult(500, "上传图片出错");
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
