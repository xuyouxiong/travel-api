package cn.linstudy.travel.controller;


import cn.linstudy.travel.domain.StrategyTheme;
import cn.linstudy.travel.qo.StrategyThemeQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.StrategyThemeService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 攻略主题控制层
*/
@Controller
@Api(tags = "攻略主题控制层")
@RequestMapping("/strategyTheme")
public class StrategyThemeController {

  @Autowired
  StrategyThemeService strategyThemeService;

  @GetMapping("list")
  @ApiOperation(value = "攻略主题的查询")
  public String list(Model model, StrategyThemeQueryObject qo){
    model.addAttribute("page",strategyThemeService.listForPage(qo));
    return "strategyTheme/list";
  }

  @ApiOperation(value = "攻略主题的删除")
  @GetMapping("delete")
  @ResponseBody()
  public JsonResult delete(Long id){
    strategyThemeService.removeById(id);
    return JsonResult.success();
  }

  @ApiOperation(value = "攻略主题的编辑或增加")
  @PostMapping("saveOrUpdate")
  @ResponseBody
  public JsonResult saveOrUpdate(Long id, StrategyTheme strategyTheme){
    // 新增
    if (id == null){
      strategyThemeService.save(strategyTheme);
    }else {
      strategyThemeService.updateById(strategyTheme);
    }
    return JsonResult.success();
  }

}
