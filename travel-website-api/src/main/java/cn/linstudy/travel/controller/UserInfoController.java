package cn.linstudy.travel.controller;


import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.vo.UserInfoLoginVO;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * 
 * @Date 2021/4/9 14:46
 */
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/users")
public class UserInfoController {

  @Autowired
  UserInfoService userInfoService;

  
  @ApiOperation(value = "根据id查询用户")
  @GetMapping("/detail")
  @ApiImplicitParam(name = "id", value = "用户id")
      public Object getUser( String id){
    return userInfoService.getById(Long.valueOf(id));
  }

  @ApiOperation(value = "根据手机查询用户")
  @GetMapping("checkPhone")
  @ApiImplicitParam(name = "phone", value = "电话号码")
  public JsonResult checkPhone( String phone){
    userInfoService.checkPhone(phone);
    System.out.println(userInfoService.checkPhone(phone));
    return userInfoService.checkPhone(phone);
  }

  @ApiOperation(value = "发送验证码")
  @GetMapping("sendVerifyCode")
  @ApiImplicitParam(name = "phone", value = "电话号码")
  public JsonResult sendVerifyCode( String phone){
   return userInfoService.sendVerifyCode(phone);
  }

  @ApiOperation(value = "用户注册")
  @PostMapping("register")
  public JsonResult sendVerifyCode( UserInfoRegisterVO userInfoRegisterVO){
    System.out.println("进入用户注册的接口");
    return userInfoService.register(userInfoRegisterVO);
  }

  @ApiOperation(value = "用户登录")
  @GetMapping("login")
  public JsonResult login(UserInfoLoginVO userInfoRegisterVO){
    return userInfoService.login(userInfoRegisterVO);
  }

  @ApiOperation(value = "用户的退出")
  public JsonResult logout(@UserParam UserInfo userInfo) {
    return new JsonResult(200, "成功");
  }

  @ApiOperation(value = "查询是否收藏")
  @GetMapping("strategies/favor")
  public JsonResult favor(Long sid ,Long userId){
    return JsonResult.success(userInfoService.favor(sid,userId));
  }

  @ApiOperation(value = "根据id获取用户")
  @GetMapping("get")
  public JsonResult getUserById(Long id){
    return JsonResult.success(userInfoService.getById(id));
  }

}
