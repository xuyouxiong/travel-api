package cn.linstudy.travel.controller;

import cn.linstudy.travel.qo.TravelQueryObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "游记控制器接口")
@RequestMapping("/ticket")
public class TicketController {

    @ApiOperation(value = "高级查询所有")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        return "ticket/list";
    }
}
