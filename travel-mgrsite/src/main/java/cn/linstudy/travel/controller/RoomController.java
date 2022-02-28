package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Room;
import cn.linstudy.travel.qo.HotelQueryObject;
import cn.linstudy.travel.qo.RoomQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.RoomService;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.vo.HotelRegisterVo;
import cn.linstudy.travel.vo.RoomRegisterVo;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "房间")
@RequestMapping("/room")
public class RoomController {

    @Autowired
    TravelService travelService;

    @Autowired
    RoomService roomService;

    @ApiOperation(value = "查询所有")
    @GetMapping("list")
    public String listForPage(Long id, Model model,@ModelAttribute("qo") RoomQueryObject qo){
        model.addAttribute("page", roomService.listForPage(qo, id));
        model.addAttribute("id", id);
        return "hotel/room";
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("roomAdd")
    public String roomAdd(Long id, Model model){
        model.addAttribute("hotel_id", id);
        return "hotel/roomAdd";
    }


    @ApiOperation(value = "查询所有")
    @GetMapping("roomEdit")
    public String roomEdit(Long id, Model model){
        Room room = roomService.getById(id);
        model.addAttribute("hotel_id", room.getHotelId());
        model.addAttribute("room", room);
        return "hotel/roomEdit";
    }

    @ApiOperation(value = "房间的编辑/新增")
    @PostMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(RoomRegisterVo roomRegisterVo) {
        System.out.println(roomRegisterVo.getPrice());
        Room room = new Room();
        room.setHotelId(roomRegisterVo.getHotel_id());
        room.setSize(roomRegisterVo.getSize());
        room.setStatus(1);
        room.setPrice(roomRegisterVo.getPrice());
        room.setImage(roomRegisterVo.getImage());
        room.setName(roomRegisterVo.getName());
        room.setNumber(roomRegisterVo.getNumber());

        if (roomRegisterVo.getId() != null) {
            room.setId(roomRegisterVo.getId());
        }
        roomService.saveOrUpdate(room);
        return new JsonResult(200, "添加成功");
    }

    @ApiOperation(value = "删除房间")
    @GetMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        roomService.removeById(id);
        return JsonResult.success();
    }
}
