package cn.linstudy.travel.mongo.service.impl;

import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.mongo.repository.StrategyCommentRepository;
import cn.linstudy.travel.mongo.service.StrategyCommentService;
import cn.linstudy.travel.qo.StrategyCommentQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.service.StrategyStatisticsRedisService;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import cn.linstudy.travel.service.StrategyService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


/**
 * @Description
 *
 * @Date 2021/4/19 13:11
 */
@Service
public class StrategyCommentServiceImpl implements StrategyCommentService {

  @Autowired
  StrategyCommentRepository strategyCommentRepository;

  @Autowired
  StrategyStatisticsRedisService strategyStatisticsRedisService;

  @Autowired
  MongoTemplate template;
  @Override
  public JsonResult commentAdd(StrategyComment strategyComment) {
    strategyComment.setId(null);
    strategyComment.setCreateTime(new Date());
    strategyCommentRepository.save(strategyComment);
    StrategyStatisticsVO vo = strategyStatisticsRedisService
        .getStrategyStatisticsVO(strategyComment.getStrategyId());
    vo.setReplyNum(vo.getReplyNum()+1);
    strategyStatisticsRedisService.setStrategyStatisticsVO(vo);
    return JsonResult.success();
  }

  @Override
  public Page<StrategyComment> queryForList(StrategyCommentQueryObject qo) {
    // ??????????????????????????????
    Query query = new Query();
    // ????????????id???????????????
    if (qo.getStrategyId() != null){
      query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
    }
    // ???????????????
    long totalCount = template.count(query, StrategyComment.class);
    // ????????????????????????????????????????????????????????????????????????
    if (totalCount == 0){
      return Page.empty();
    }
    // ??????????????????,??????????????????????????????0???????????????????????????
    Pageable pageable = PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize());
    query.with(pageable);
    // ??????strategyId?????????????????????
    List<StrategyComment> strategyComments = template.find(query, StrategyComment.class);
    return new PageImpl(strategyComments,pageable,totalCount);
  }

}
