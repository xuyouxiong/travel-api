package cn.linstudy.travel.controller;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.TravelContent;
import cn.linstudy.travel.mongo.domain.TravelComment;
import cn.linstudy.travel.mongo.service.TravelCommentService;
import cn.linstudy.travel.qo.TravelConditionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.TravelContentService;
import cn.linstudy.travel.service.TravelService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/17 20:00
 */
@Controller
@RequestMapping("/travels")
public class TravelController {

  @Autowired
  TravelService travelService;

  @Autowired
  TravelContentService travelContentService;

  @Autowired
  TravelCommentService travelCommentService;

  /**
   * @Description: 根据目的地id查询游记
   * @author XiaoLin
   * @date 2021/4/17
   * @Param: [destId]
   * @return cn.linstudy.travel.qo.response.JsonResult
   */
  @GetMapping("query")
  @ResponseBody
  public JsonResult query(TravelConditionQueryObject qo,Long destId){
    return travelContentService.queryByDestId(qo,destId);
  }

  /**
   * @Description: 根据id查询游记细节
   * @author XiaoLin
   * @date 2021/4/17
   * @Param: [destId]
   * @return cn.linstudy.travel.qo.response.JsonResult
   */
  @GetMapping("detail")
  @ResponseBody
  public JsonResult detail(Long destId,Long id){
    return travelContentService.detail(destId,id);
  }

  /**
      * @Description: 查询前三攻略
      * @author XiaoLin
      * @date 2021/4/19
      * @Param: [destId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @GetMapping("viewnumTop3")
  @ResponseBody
  public JsonResult viewnumTop3(Long destId){
    return travelService.viewnumTop3(destId);
  }

  @PostMapping("commentAdd")
  @ResponseBody
  public JsonResult commentAdd(TravelComment travelComment, HttpServletRequest servletRequest){
    return travelCommentService.commentAdd(travelComment,servletRequest);
  }

  /**
      * @Description: 查看评论
      * @author XiaoLin
      * @date 2021/4/20
      * @Param: [travelId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @PassLogin
  @GetMapping("comments")
  @ResponseBody
  public JsonResult getComments(Long travelId){
    List<TravelComment> travelComments = travelCommentService.queryByTravelId(travelId);
    return JsonResult.success(travelComments);
  }

}
