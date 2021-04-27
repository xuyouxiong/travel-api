package cn.linstudy.travel.controller;

import cn.linstudy.travel.qo.TravelConditionQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.TravelContentService;
import cn.linstudy.travel.service.TravelService;
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
 * @Author XiaoLin
 * @Date 2021/4/17 14:09
 */
@Controller
@Api(tags = "游记控制器接口")
@RequestMapping("/travel")
public class TravelController {

  @Autowired
  TravelService travelService;

  @Autowired
  TravelContentService travelContentService;

  /**
      * @Description: 高级查询所有
      * @author XiaoLin
      * @date 2021/4/17
      * @Param: [qo, model]
      * @return java.lang.String
      */
  @ApiOperation(value = "高级查询所有")
  @GetMapping("list")
  public String listForPage(@ModelAttribute("qo") TravelQueryObject qo, Model model){
    model.addAttribute("page",travelService.listForPage(qo));
    return "travel/list";
  }

/**
    * @Description: 通过id获取内容
    * @author XiaoLin
    * @date 2021/4/17
    * @Param: [id]
    * @return cn.linstudy.travel.qo.response.JsonResult
    */
  @ApiOperation(value = "攻略主题的查询")
  @GetMapping("getContentById")
  @ResponseBody
  public JsonResult getContentById(Long id){
    return JsonResult.success(travelContentService.getContentById(id));
  }

  /**
      * @Description: 游记审核
      * @author XiaoLin
      * @date 2021/4/17
      * @Param: [id, state]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @GetMapping("audit")
  @ResponseBody
  public JsonResult audit(Long id , Integer state){
    return travelContentService.audit(id,state);
  }


}
