package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String listAll(Model model, @ModelAttribute("qo") DestinationQueryObject qo){
        model.addAttribute("page",destinationService.queryList(qo));
        model.addAttribute("toasts",destinationService.getToasts(qo.getParentId()));
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


}
