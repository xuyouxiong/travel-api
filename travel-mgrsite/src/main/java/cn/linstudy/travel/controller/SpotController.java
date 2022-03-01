package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Spot;
import cn.linstudy.travel.qo.OrderQueryObject;
import cn.linstudy.travel.qo.SpotQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.OrderService;
import cn.linstudy.travel.service.SpotService;
import cn.linstudy.travel.vo.HotelRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "景点")
@RequestMapping("/spot")
public class SpotController {
    @Autowired
    OrderService orderService;

    @Autowired
    SpotService spotService;

    @ApiOperation(value = "高级查询所有")
    @GetMapping("list")
    public String listForPage(@ModelAttribute("qo") SpotQueryObject qo, Model model, Long uid){
        model.addAttribute("page",spotService.listForPage(qo,uid));
        model.addAttribute("uid", uid);
        return "spot/list";
    }

    @ApiOperation(value = "新增门票")
    @GetMapping("input")
    public String addSpot(Model model, Long uid){
        model.addAttribute("uid", uid);
        return "spot/input";
    }

    @ApiOperation(value = "编辑门票")
    @GetMapping("edit")
    public String edit(Model model, Long uid, Long id){
        model.addAttribute("spot", spotService.getById(id));
        model.addAttribute("uid", uid);
        return "spot/edit";
    }

    @ApiOperation(value = "景点门票的编辑/新增")
    @PostMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Spot spot) {
        spotService.saveOrUpdate(spot);
        return new JsonResult(200, "添加成功");
    }

    @ApiOperation(value = "删除景点门票")
    @GetMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        spotService.removeById(id);
        return JsonResult.success();
    }
}
