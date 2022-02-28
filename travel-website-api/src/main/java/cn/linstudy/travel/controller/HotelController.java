package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.qo.HotelFrontQueryObject;
import cn.linstudy.travel.qo.TravelConditionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("query")
    @ResponseBody
    public JsonResult query(HotelFrontQueryObject qo){
        List<Hotel> hotelList = hotelService.queryByDestId(qo);
        return new JsonResult(200, "成功", hotelList);
    }


}
