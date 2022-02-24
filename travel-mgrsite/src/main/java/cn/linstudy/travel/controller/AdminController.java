package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.qo.AdminQueryObject;
import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.qo.response.LoginResponse;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.service.*;
import cn.linstudy.travel.utils.JwtUtil;
import cn.linstudy.travel.vo.AdminInfoVo;
import cn.linstudy.travel.vo.AdminRegisterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "管理员相关接口")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    DestinationService destinationService;

    @Autowired
    RegionService regionService;

    // AdminService adminService = new AdminService();
    @Autowired
    AdminService adminService;

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "查询所有目的地")
    @RequestMapping("list")
    public String listAll(Model model, @ModelAttribute("qo") AdminQueryObject qo){
        model.addAttribute("page", adminService.queryList(qo));
//        model.addAttribute("toasts",destinationService.getToasts(qo.getParentId()));
        return "admin/list";
    }


    @PassLogin
    @ApiOperation(value = "查询所有目的地")
    @PostMapping("login")
    @ResponseBody
    public JsonResult login(AdminInfoVo adminInfoVo){
        JsonResult result = adminService.login(adminInfoVo);
        return result;
    }


    @PassLogin
    @ApiOperation(value = "注册")
    @PostMapping("register")
    @ResponseBody
    public JsonResult register(AdminRegisterVo registerVo){
        boolean isExist = adminService.checkName(registerVo.getName());
        if (!isExist) {
            return new JsonResult(402, "该用户名已经被注册");
        }
        adminService.register(registerVo);
        return new JsonResult(200, "注册成功");
    }


    @ApiOperation(value = "下载证书")
    @GetMapping("downloadCert")
    @ResponseBody
    public JsonResult downloadCert(Integer id,HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        System.out.println(id);
        download(request, response);
        return new JsonResult(200,  "下载成功");
    }

    public static void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "222.jpg".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("/Users/sunmaoyun/Downloads/1111.jpg");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void download( HttpServletRequest request, HttpServletResponse response) {
        //前端页面将自己需要的文件名字拿过来。这个名字直接拼接到文件所在服务器的相对路径。这里为便于测试。我直接把名字写死，以后使用的时候
        //根据实际业务进行修改。
        try {
            //mac系统，所以路径是这样子的。win系统就是D盘什么什么的
            String path = "/Users/sunmaoyun/Downloads/1111.jpg";
            //这里是下载以后的文件叫做什么名字。我这里是以时间来定义名字的。
            downCfg(System.currentTimeMillis()+".jpg", request, response);
            OutputStream out;
            FileInputStream inputStream = new FileInputStream(path);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void downCfg(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 判断浏览器，进行不同的加密，这样下载的时候保存的文件名就不会乱码
        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
    }
}
