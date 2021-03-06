package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.Tags;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.mongo.service.StrategyCommentService;
import cn.linstudy.travel.qo.StrategyCommentQueryObject;
import cn.linstudy.travel.qo.StrategyQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.qo.response.RecommendReponse;
import cn.linstudy.travel.qo.response.TagsCountResponse;
import cn.linstudy.travel.redis.service.StrategyStatisticsRedisService;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import cn.linstudy.travel.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 攻略相关控制器
 * 
 * @Date 2021/4/15 22:15
 */
@Controller
@RequestMapping("strategies")
@Api(tags = "前端攻略相关接口")
public class StrategyController {

  @Autowired
  StrategyRankService strategyRankService;

  @Autowired
  StrategyService strategyService;

  @Autowired
  StrategyThemeService strategyThemeService;

  @Autowired
  StrategyConditionService strategyConditionService;

  @Autowired
  StrategyCommentService strategyCommentService;

  @Autowired
  UserInfoRedisService userInfoRedisService;

  @Autowired
  StrategyStatisticsRedisService strategyStatisticsRedisService;

  @Autowired
  TagsService tagsService;

  @PassLogin
  @ApiOperation(value = "查询前三攻略")
  @GetMapping("viewnumTop3")
  @ResponseBody
  public JsonResult viewnumTop3(Long destId){
    return strategyService.viewnumTop3(destId);
  }

  @PassLogin
  @ApiOperation(value = "根据id查询攻略细节")
  @GetMapping("detail")
  @ResponseBody
  public JsonResult detail(Long id){
    return strategyService.detail(id);
  }

  @PassLogin
  @ApiOperation(value = "查询所有主题")
  @GetMapping("themes")
  @ResponseBody
  public JsonResult listThemes(){
    return JsonResult.success(strategyThemeService.list(null));
  }

  @PassLogin
  @ApiOperation(value = "分页查询所有攻略")
  @GetMapping("query")
  @ResponseBody
  public JsonResult queryStrategy(@ModelAttribute("qo") StrategyQueryObject qo){
    System.out.println(qo.getKeyword());
    return JsonResult.success(strategyService.listForPage(qo));
  }

  @PassLogin
  @ApiOperation(value = "攻略排行")
  @GetMapping("rank")
  @ResponseBody
  public JsonResult rank(Long type){
    return strategyService.rank(type);
  }

  @PassLogin
  @ApiOperation(value = "旅游攻略导航")
  @GetMapping("condition")
  @ResponseBody
  public JsonResult condition(Integer type){
    return strategyConditionService.condition(type);
  }

  @PassLogin
  @ApiOperation(value = "攻略评论")
  @PostMapping("commentAdd")
  @ResponseBody
  public JsonResult commentAdd(StrategyComment strategyComment, HttpServletRequest httpServletRequest){
    UserInfo userInfo = userInfoRedisService.getUserInfoByToken(httpServletRequest.getHeader("token"));
    BeanUtils.copyProperties(userInfo,strategyComment);
    return strategyCommentService.commentAdd(strategyComment);
  }

  @PassLogin
  @ApiOperation(value = "分页查看攻略评论")
  @GetMapping("comments")
  @ResponseBody
  public JsonResult getComments(StrategyCommentQueryObject qo){

    return JsonResult.success(strategyCommentService.queryForList(qo));
  }

  @PassLogin
  @ApiOperation(value = "查询统计数量")
  @GetMapping("statistic")
  @ResponseBody
  public JsonResult getStatistic(@RequestParam("sid") Long strategyId)
      throws InvocationTargetException, IllegalAccessException {
    return strategyStatisticsRedisService.increaseViewNum(strategyId);
  }

  @ApiOperation(value = "收藏攻略")
  @PostMapping("favor")
  @ResponseBody
  public JsonResult favorStrategy(@RequestParam("sid") Long strategyId,@UserParam UserInfo userInfo) {
    return JsonResult.success(strategyStatisticsRedisService.favorStrategy(strategyId,userInfo.getId()));
  }

  @ApiOperation(value = "点赞功能")
  @PostMapping("strategyThumbup")
  @ResponseBody
  public JsonResult strategyThumbup(@RequestParam("sid") Long strategyId,@UserParam UserInfo userInfo) {
    return strategyStatisticsRedisService.strategyThumbup(strategyId,userInfo.getId());
  }



  @ApiOperation(value = "推荐功能")
  @GetMapping("strategyRecommend")
  @ResponseBody
  public JsonResult strategyRecommend(@UserParam UserInfo userInfo, @ModelAttribute("qo") StrategyQueryObject qo) {
    Tags tags = tagsService.getById(1);
    Long theme_id = tags.getThemeId();
    qo.setThemeId(tags.getThemeId());
    List<Strategy> strategyList = strategyService.getRecommend(qo);
    List<Strategy> result = new ArrayList<>();
    RecommendReponse recommendReponse = new RecommendReponse();
    if (strategyList.size() >= 3) {
      for(int i = 0; i < 3; ++i) {
        result.add(strategyList.get(i));
      }
      recommendReponse.setRecords(result);
    } else {
      // 这边要补的
      // 找到这个用户评论类型最多的其中一个(这个类型不等于后台设置的)
      //
      List<TagsCountResponse> tagsCountResponseList = tagsService.selectCountTags(userInfo.getId(),theme_id);
      List<Strategy> strategyList1 = new ArrayList<>();
      if(tagsCountResponseList.size() > 0) {
        strategyList1 = strategyService.queryByThemeId(tagsCountResponseList.get(0).getTagId());
      }

      List<Strategy> results = strategyRankService.getStrategys();
      if (strategyList.size() == 0) {
        if (tagsCountResponseList.size() == 0) {
          recommendReponse.setRecords(results);
        } else {
          for(int j = 0; j < strategyList1.size() && j < 3; ++j) {
            result.add(strategyList1.get(j));
          }

          if (strategyList1.size() <= 3) {
            for(int j = 0; j < 3 - strategyList1.size(); ++j) {
              result.add(results.get(j));
            }
          }

          recommendReponse.setRecords(result);
        }
      }

      if (strategyList.size() == 1) {
        result.add(strategyList.get(0));
        if (tagsCountResponseList.size() == 0) {
          for(int j = 0; j < 2; j++) {
            result.add(results.get(j));
          }
          recommendReponse.setRecords(result);
        } else {
          for(int j = 0; j < strategyList1.size() && j < 2; ++j) {
            result.add(strategyList1.get(j));
          }

          if (strategyList1.size() <= 2) {
            for(int j = 0; j < 2 - strategyList1.size(); ++j) {
              result.add(results.get(j));
            }
          }

          recommendReponse.setRecords(result);
        }
      }

      if (strategyList.size() == 2) {
        result.add(strategyList.get(0));
        result.add(strategyList.get(1));
        if (tagsCountResponseList.size() == 0) {
          for(int j = 0; j < 1; j++) {
            result.add(results.get(j));
          }
          recommendReponse.setRecords(result);
        } else {
          for(int j = 0; j < strategyList1.size() && j < 1; ++j) {
            result.add(strategyList1.get(j));
          }

          if (strategyList1.size() <= 1) {
            for(int j = 0; j < 1 - strategyList1.size(); ++j) {
              result.add(results.get(j));
            }
          }

          recommendReponse.setRecords(result);
        }
      }
    }

    recommendReponse.setCurrent(1);
    recommendReponse.setSize(1);
    recommendReponse.setTotal(recommendReponse.getRecords().size());
    recommendReponse.setPages(1);
    return new JsonResult(200, "成功", recommendReponse);
  }



}
