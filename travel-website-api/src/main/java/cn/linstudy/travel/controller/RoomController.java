package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Room;
import cn.linstudy.travel.qo.HotelFrontQueryObject;
import cn.linstudy.travel.qo.RoomFrontQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.HotelService;
import cn.linstudy.travel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    HotelService hotelService;

    @GetMapping("query")
    @ResponseBody
    public JsonResult query(RoomFrontQueryObject qo){
        List<Room> hotelList = roomService.queryByDestId(qo);
        return new JsonResult(200, "成功", hotelList);
    }

    @GetMapping("getDetail")
    @ResponseBody
    public JsonResult getDetail(Long id){
        Room room = roomService.getById(id);
        room.setHotelName(hotelService.getById(room.getHotelId()).getName());
        /**
         * 下单的工作
         */
        return new JsonResult(200, "成功", room);
    }
}
