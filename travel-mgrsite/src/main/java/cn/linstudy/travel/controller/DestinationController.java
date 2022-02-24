package cn.linstudy.travel.controller;

import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * 
 * @Date 2021/4/13 18:34
 */
@Controller
@Api(tags = "目的地相关接口")
@RequestMapping("/destination")
public class DestinationController {

  @Autowired
  DestinationService destinationService;

  @Autowired
  RegionService regionService;

  @ApiOperation(value = "查询所有目的地")
  @RequestMapping("list")
  public String listAll(Model model,@ModelAttribute("qo") DestinationQueryObject qo){
    model.addAttribute("page",destinationService.queryList(qo));
    model.addAttribute("toasts",destinationService.getToasts(qo.getParentId()));
    return "destination/list";
  }

  @ApiOperation(value = "删除目的地")
  @GetMapping("delete")
  @ResponseBody
  public JsonResult delete(Long id){
    destinationService.deleteById(id);
    return JsonResult.success();
  }



}
