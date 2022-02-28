package cn.linstudy.travel.controller;

import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.service.TravelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "用户制器接口")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    TravelService travelService;


    @ApiOperation(value = "高级查询所有")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        model.addAttribute("page",travelService.listForPage(qo));
        return "order/list";
    }
}
