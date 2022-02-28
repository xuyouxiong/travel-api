package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.UserInfoQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "用户制器接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;


    @ApiOperation(value = "查询用户")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") UserInfoQueryObject qo, Model model){
        model.addAttribute("page",userInfoService.listForPage(qo));
        return "user/list";
    }

    @ApiOperation(value = "删除")
    @GetMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        userInfoService.removeById(id);
        return JsonResult.success();
    }

    @GetMapping("audit")
    @ResponseBody
    public JsonResult audit(Long id , Integer state){
        UserInfo userInfo = userInfoService.getById(id);
        userInfo.setState(state);
        userInfoService.saveOrUpdate(userInfo);
        return new JsonResult(200, "成功");
    }
}
