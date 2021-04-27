package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.domain.Visitor;
import cn.linstudy.travel.domain.VisitorNum;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.service.VisitorNumService;
import cn.linstudy.travel.service.VisitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author XiaoLin
 */
@RestController
@Api(tags = "访客相关接口")
@RequestMapping("/visitor")
public class VisitorController {

  @Autowired
  VisitorService visitorService;

  @Autowired
  UserInfoService userInfoService;

  @Autowired
  VisitorNumService visitorNumService;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @ApiOperation(value = "查询用户的所有访客信息")
  @GetMapping("/query")
  @ApiImplicitParam(name = "ownerId", value = "用户id")
  public JsonResult query(Long ownerId,@UserParam UserInfo userInfo){
    // 先增加访客数量
    visitorNumService.addVistorNum(ownerId,userInfo.getId());
    // 查询访客信息返回给前台
    List<Visitor> visitors = visitorService.queryVisitor(ownerId,userInfo.getId());
    // 查询访客数量信息给前台
    VisitorNum visitorNum = visitorNumService.queryVisitorNum(ownerId);
    HashMap<String, Object> map = new HashMap<>();
    map.put("visitors",visitors);
    map.put("visitorNum",visitorNum);
    return JsonResult.success(map);
  }


}
