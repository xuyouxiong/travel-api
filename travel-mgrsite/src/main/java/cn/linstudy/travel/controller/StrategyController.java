package cn.linstudy.travel.controller;


import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.qo.StrategyQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.StrategyCatalogService;
import cn.linstudy.travel.service.StrategyContentService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.StrategyThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 攻略控制层
*/
@Controller
@Api(tags = "攻略控制层")
@RequestMapping("strategy")
public class StrategyController {

  @Autowired
  StrategyService strategyService;

  @Autowired
  StrategyCatalogService strategyCatalogService;

  @Autowired
  StrategyThemeService strategyThemeService;

  @Autowired
  StrategyContentService strategyContentService;

  @GetMapping("list")
  @ApiOperation(value = "查询所有攻略")
  public String list(Model model,StrategyQueryObject qo){
    model.addAttribute("page",strategyService.listForPage(qo));
    return "strategy/list";
  }

  @ApiOperation(value = "删除攻略")
  @GetMapping("delete")
  @ResponseBody
  public JsonResult delete(Long id){
    strategyService.removeById(id);
    return JsonResult.success();
  }

  @GetMapping("changeState")
  @ApiOperation(value = "修改状态")
  @ResponseBody
  public JsonResult changeState(Integer state,Long id){
    return strategyService.updateState(state,id);
  }

  @GetMapping("input")
  @ApiOperation(value = "攻略跳转到增加页或者编辑页")
  public String input(Long id,Model model){
    if (id != null ){
      Strategy strategy = strategyService.getById(id);
      strategy.setContent(strategyContentService.getById(strategy.getId()));
      model.addAttribute(strategy);
    }
      model.addAttribute("catalogs", strategyCatalogService.listCatalog());
      model.addAttribute("themes",strategyThemeService.list());
    return "strategy/input";
  }

  @PostMapping("saveOrUpdate")
  @ApiOperation(value = "攻略编辑或者新增")
  @ResponseBody
  public JsonResult saveOrUpdate(Strategy strategy){
  strategyService.saveOrUpdate(strategy);
  return JsonResult.success();
  }


}
