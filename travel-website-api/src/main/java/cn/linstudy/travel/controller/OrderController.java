package cn.linstudy.travel.controller;


import cn.linstudy.travel.annotation.UserParam;
import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.qo.OrderAddObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("getOrder")
    @ResponseBody
    public JsonResult getOrders(Long uid) {
        System.out.println(uid);
        List<Orders> orders = orderService.getOrders(uid);
        return new JsonResult(200, "成功", orders);
    }

    @GetMapping("cancelOrder")
    @ResponseBody
    public JsonResult cancelOrder(Long id) {
        Orders orders = orderService.getById(id);
        orders.setStatus(2);
        orderService.saveOrUpdate(orders);
        return new JsonResult(200, "成功");
    }

    @PostMapping("setOrder")
    @ResponseBody
    public JsonResult setOrder(OrderAddObject orderAddObject, @UserParam UserInfo userInfo){
        Orders order = new Orders();
        order.setOrderSn(createOrderCode(new Date()));
        order.setUid(userInfo.getId());
        order.setUsername(userInfo.getNickname());
        order.setInfo(orderAddObject.getInfo());
        if (orderAddObject.getType().equals("酒店")) {
            order.setHotelName(orderAddObject.getHotelName());
            order.setRoomId(orderAddObject.getRoomId());
            order.setRoomName(orderAddObject.getRoomName());
            order.setHotelName(orderAddObject.getHotelName());
            order.setHotelId(orderAddObject.getHotelId());
        }
        order.setIdCard(orderAddObject.getIdCard());
        order.setStatus(1);
        order.setPrice(orderAddObject.getPrice());
        order.setName(orderAddObject.getName());
        order.setPhone(orderAddObject.getPhone());
        order.setType(orderAddObject.getType());
        orderService.saveOrUpdate(order);
        return new JsonResult(200, "下单成功");

    }

    private static String createOrderCode(Date date) {
        DateFormat format = new SimpleDateFormat("yyMMdd");
        if(null == date){
            date = new Date();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(format.format(date));
        String code = buffer.toString();

        //随机生成四位数
        String timeMillis = System.currentTimeMillis() + "";
        String randomNum = timeMillis.substring(timeMillis.length() - 4);

        //生成订单编号
        String orderCode = code + randomNum;

        return orderCode;
    }

}
