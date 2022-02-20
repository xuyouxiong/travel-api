package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.BannerService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * 
 * @Date 2021/4/22 20:56
 */
@RestController
@RequestMapping("/banners")
public class BannerController {

  @Autowired
  BannerService bannerService;

  @Autowired
  TravelService travelService;

  @Autowired
  StrategyService strategyService;

  @PassLogin
  @GetMapping("travel")
  public JsonResult getTravel(){
    return JsonResult.success(travelService.list(null));
  }

  @GetMapping("strategy")
  public JsonResult getStrategy(){
    return JsonResult.success(strategyService.list(null));
  }

}
