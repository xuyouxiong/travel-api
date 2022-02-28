package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.AdminParam;
import cn.linstudy.travel.domain.Admin;
import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.qo.HotelQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.*;
import cn.linstudy.travel.vo.HotelRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "用户制器接口")
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    TravelService travelService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    StrategyContentService strategyContentService;

    @Autowired
    StrategyCatalogService strategyCatalogService;

    @Autowired
    StrategyThemeService strategyThemeService;

    @Autowired
    HotelService hotelService;

    @ApiOperation(value = "高级查询所有")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") HotelQueryObject qo, Model model,Long uid){
        model.addAttribute("page",hotelService.listForPage(qo, uid));
        return "hotel/list";
    }

    @GetMapping("input")
    @ApiOperation(value = "酒店的增加")
    public String input(Long id,Model model){
        if (id != null ){
            Strategy strategy = strategyService.getById(id);
            strategy.setContent(strategyContentService.getById(strategy.getId()));
            model.addAttribute(strategy);
        }
        model.addAttribute("catalogs", strategyCatalogService.listCatalog());
        model.addAttribute("themes",strategyThemeService.list());
        return "hotel/input";
    }

    @GetMapping("edit")
    @ApiOperation(value = "酒店的增加")
    public String edit(Long id,Model model){
        model.addAttribute("hotel", hotelService.getById(id));
        System.out.println(hotelService.getById(id).getLowPrice());
        return "hotel/edit";
    }

    @ApiOperation(value = "酒店的编辑/新增")
    @PostMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(HotelRegisterVo hotelRegisterVo) {
        Hotel hotel = new Hotel();
        hotel.setStatus(1);
        hotel.setUid(hotelRegisterVo.getUid());
        hotel.setAddress(hotelRegisterVo.getAddress());
        hotel.setImage(hotelRegisterVo.getImage());
        hotel.setName(hotelRegisterVo.getName());
        hotel.setLowPrice(hotelRegisterVo.getLow_price());
        hotel.setSummary(hotelRegisterVo.getSummary());
        hotel.setStartDate(hotelRegisterVo.getStartDate());
        hotel.setEndDate(hotelRegisterVo.getEndDate());
        if (hotelRegisterVo.getId() != null) {
            hotel.setId(hotelRegisterVo.getId());
        }
        hotelService.saveOrUpdate(hotel);
        return new JsonResult(200, "添加成功");
    }


    @ApiOperation(value = "删除就酒店")
    @GetMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        hotelService.removeById(id);
        return JsonResult.success();
    }


}
