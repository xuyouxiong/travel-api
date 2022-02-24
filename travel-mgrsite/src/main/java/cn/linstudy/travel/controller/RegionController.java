package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.RegionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * 
 * @Date 2021/4/13 9:09
 */
@Controller
@Api(tags = "地区相关接口")
@RequestMapping("/region")
public class RegionController {

  @Autowired
  RegionService regionService;

  @Autowired
  DestinationService destinationService;



  @ApiOperation(value = "查询所有热门")
  @RequestMapping ("list")
  public String listAll(Model model, @ModelAttribute("qo") RegionQueryObject qo){
    DestinationQueryObject destinationQueryObject = new DestinationQueryObject();
    model.addAttribute("page",regionService.queryPage(qo));
    model.addAttribute("dests",destinationService.listAll(destinationQueryObject));
    return "region/list";
  }

  @ApiOperation(value = "增加或者修改")
  @RequestMapping("saveOrUpdate")
  @ResponseBody
  public JsonResult saveOrUpdate(Region region){
    if (region.getId() == null){
      // 为空说明为增加
      return regionService.insert(region);
    }else {
      regionService.updateById(region);
      return JsonResult.success();
    }
  }

  @ApiOperation(value = "修改为热门或者是普通")
  @RequestMapping("changeHotValue")
  @ResponseBody
  public JsonResult changeHotValue(Boolean hot,Long id){
    regionService.changeHotValue(hot,id);
    return JsonResult.success();
  }

  @ApiOperation(value = "删除地区")
  @RequestMapping("delete")
  @ResponseBody
  public JsonResult delete(Long id){
    return regionService.delete(id);
  }
}
