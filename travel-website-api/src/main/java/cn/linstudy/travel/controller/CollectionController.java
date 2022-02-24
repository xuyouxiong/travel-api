package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.Collection;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.service.CollectionService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.UserInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 收藏控制器
 *
 * @Date 2021/4/23 20:39
 */
@Controller
@Api(tags = "前端收藏相关接口")
@RequestMapping("/collections")
public class CollectionController {

  @Autowired
  StrategyService strategyService;

  @Autowired
  StringRedisTemplate stringRedisTemplate;
  @ApiOperation(value = "收藏列表")
  @GetMapping("query")
  @ResponseBody
  public JsonResult query(@UserParam UserInfo userInfo){
    List<Strategy> strategyList = new ArrayList<>();
    // 拼接key
    String key = RedisKeyEnum.COLLECTION_STRATEGY.join(userInfo.getId().toString());
    String list = stringRedisTemplate.opsForValue().get(key).toString();
    List<Long> strategyIds = JSON.parseArray(list, Long.class);
    for (Long strategyId : strategyIds) {
      strategyList.add(strategyService.getById(strategyId));
    }
    return JsonResult.success(strategyList);
  }
}
