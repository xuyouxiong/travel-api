package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.qo.StrategyCatalogQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.StrategyCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 攻略分类控制层
*/
@Controller
@Api(tags = "攻略分类控制层")
@RequestMapping("strategyCatalog")
public class StrategyCatalogController {

  @Autowired
  StrategyCatalogService strategyCatalogService;

  @Autowired
  DestinationService destinationService;
  @ApiOperation(value = "攻略分类查询所有")
  @GetMapping("list")
  public String list(Model model,StrategyCatalogQueryObject qo){
    model.addAttribute("page",strategyCatalogService.listForPage(qo));
    model.addAttribute("dests",destinationService.list(null));
    return "strategyCatalog/list";
  }

  @ApiOperation(value = "删除攻略分类")
  @GetMapping("delete")
  @ResponseBody
  public JsonResult delete(Long id){
    strategyCatalogService.removeById(id);
    return JsonResult.success();
  }

  @ApiOperation(value = "增加或者编辑")
  @PostMapping("saveOrUpdate")
  @ResponseBody
  public JsonResult saveOrUpdate(Long id, StrategyCatalog strategyCatalog){
    if (id == null ){
      Destination destination = destinationService.getById(strategyCatalog.getDestId());
      strategyCatalog.setDestName(destination.getName());
      strategyCatalogService.save(strategyCatalog);
    }else {
      Destination destination = destinationService.getById(strategyCatalog.getDestId());
      strategyCatalog.setDestName(destination.getName());
      strategyCatalogService.updateById(strategyCatalog);
    }
    return JsonResult.success();
  }


}
