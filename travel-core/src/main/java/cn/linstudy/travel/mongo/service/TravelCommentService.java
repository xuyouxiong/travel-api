package cn.linstudy.travel.mongo.service;

import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.mongo.domain.TravelComment;
import cn.linstudy.travel.qo.response.JsonResult;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/19 21:56
 */
public interface TravelCommentService {

  JsonResult commentAdd(TravelComment travelComment, HttpServletRequest httpServletRequest);

  List<TravelComment> queryByTravelId(Long travelId);
}
