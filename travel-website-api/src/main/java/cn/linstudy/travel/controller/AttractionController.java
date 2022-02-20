package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Attraction;
import cn.linstudy.travel.qo.AttractionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 景点控制器
 * 
 * @Date 2021/4/14 18:41
 */
@RestController
@RequestMapping("/attractions")
public class AttractionController {

  @Autowired
  AttractionService attractionService;

  @GetMapping("list")
  public JsonResult list(AttractionQueryObject qo){
    return attractionService.listForPage(qo);
  }

  @GetMapping("detail")
  public JsonResult detail(Long id){
    return attractionService.detail(id);
  }
}
