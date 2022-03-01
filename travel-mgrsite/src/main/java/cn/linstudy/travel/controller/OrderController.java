package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.qo.OrderQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.OrderService;
import cn.linstudy.travel.service.TravelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "用户制器接口")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    TravelService travelService;

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "高级查询所有")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") OrderQueryObject qo, Model model){
        model.addAttribute("page",orderService.listForPage(qo));
        return "order/list";
    }

    @ApiOperation(value = "订单")
    @GetMapping("audit")
    @ResponseBody
    public JsonResult setOrder(Long id, Integer state) {
        Orders orders = orderService.getById(id);
        orders.setStatus(state);
        orderService.saveOrUpdate(orders);
        return new JsonResult(200, "成功");
    }

}
