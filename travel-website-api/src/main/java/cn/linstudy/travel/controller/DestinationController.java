package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;
import cn.linstudy.travel.service.StrategyCatalogService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.vo.StrategyCatalogVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 前端界面的目的地控制器接口
 * @Author XiaoLin
 * @Date 2021/4/13 21:25
 */
@Controller
@Api(tags = "前端目的地相关接口")
@RequestMapping("/destinations")
public class DestinationController {

  @Autowired
  RegionService regionService;

  @Autowired
  DestinationService destinationService;

  @Autowired
  StrategyService strategyService;

  @Autowired
  StrategyCatalogService strategyCatalogService;

  @PassLogin
  @ApiOperation(value = "查找热门目的地")
  @GetMapping("hotRegion")
  @ResponseBody
  public JsonResult getHotRegion(){
    return regionService.getHotRegion();
  }

  @PassLogin
  @ApiOperation(value = "根据地区查找下面目的地")
  @GetMapping("search")
  @ResponseBody
  public JsonResult searchDestination(Long regionId){
    List<Destination> destinations = destinationService.searchDestination(regionId);
    return JsonResult.success(destinations);
  }

  @PassLogin
  @ApiOperation(value = "根据地区id查询前端界面吐司")
  @GetMapping("toasts")
  @ResponseBody
  public JsonResult searchDestination(Model model,Long destId){
    List<Destination> toasts = destinationService.getToasts(destId);
    return JsonResult.success(toasts);
  }
  @PassLogin
  @ApiOperation(value = "根据地区id查询前端界面概况")
  @GetMapping("catalogs")
  @ResponseBody
  public JsonResult listCatalogs(Long destId){
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("dest_id",destId);
    return JsonResult.success( strategyCatalogService.list(queryWrapper));
  }



}
