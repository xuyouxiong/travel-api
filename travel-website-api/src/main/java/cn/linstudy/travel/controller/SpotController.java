package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.Spot;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spot")
public class SpotController {
    @Autowired
    SpotService spotService;


    @GetMapping("getSpots")
    @ResponseBody
    public JsonResult getSpots() {
        List<Spot> orders = spotService.getSpots();
        return new JsonResult(200, "成功", orders);
    }

    @GetMapping("getDetail")
    @ResponseBody
    public JsonResult getDetail(Long id) {
        return new JsonResult(200, "成功", spotService.getById(id));
    }
}
