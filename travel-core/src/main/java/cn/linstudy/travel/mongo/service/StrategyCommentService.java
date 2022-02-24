package cn.linstudy.travel.mongo.service;

import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.qo.StrategyCommentQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import org.springframework.data.domain.Page;

/**
 * @Description 攻略评论的服务层接口
 * 
 * @Date 2021/4/19 13:09
 */
public interface StrategyCommentService {

  JsonResult commentAdd(StrategyComment strategyComment);

  Page<StrategyComment> queryForList(StrategyCommentQueryObject qo);
}
