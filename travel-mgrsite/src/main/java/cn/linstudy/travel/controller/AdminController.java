package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.qo.response.LoginResponse;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;
import cn.linstudy.travel.utils.JwtUtil;
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

    @ApiOperation(value = "查询所有目的地")
    @RequestMapping("list")
    public String listAll(Model model, @ModelAttribute("qo") DestinationQueryObject qo){
        model.addAttribute("page",destinationService.queryList(qo));
        model.addAttribute("toasts",destinationService.getToasts(qo.getParentId()));
        return "admin/list";
    }

    @ApiOperation(value = "查询所有目的地")
    @PostMapping("login")
    @ResponseBody
    public JsonResult login(String username, String password){
//        QueryWrapper wrapper = new QueryWrapper();
//        Map<String, String> map = new HashMap<>();//用来存放payload
//        map.put("phone",userInfo.getPhone());
//        String token = JwtUtil.getToken(Long.toString(userInfo.getId()),map);
//        String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
//        String LoginToken = userInfoRedisService.getValue(key);
//        if (LoginToken != null){
//            userInfoRedisService.resetTime(key);
//        }
//        // 将token放入redis中
////       String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
//        userInfoRedisService.setLoginToekn(key,token);
//        Map<String,Object> resultMap = new HashMap<>();
//        resultMap.put("token",token);
//        resultMap.put("user",userInfo);
//        return  JsonResult.success(resultMap);
//        throw new LogicException("手机号未注册");
//        System.out.println(username + ":" + password);
        JsonResult result = new JsonResult(200, "成功", new LoginResponse(1, "成功登录"));
        return result;
    }
}
