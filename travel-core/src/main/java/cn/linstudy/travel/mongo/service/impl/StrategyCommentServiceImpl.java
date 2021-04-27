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
 * @Author XiaoLin
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
    // 分页得使用传统的分页
    Query query = new Query();
    // 看看攻略id是否为空值
    if (qo.getStrategyId() != null){
      query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
    }
    // 计算总页数
    long totalCount = template.count(query, StrategyComment.class);
    // 如果没数据，就不需要继续分页了，直接返回一个空集
    if (totalCount == 0){
      return Page.empty();
    }
    // 执行分页逻辑,第一个参数因为下标从0开始，所以需要减一
    Pageable pageable = PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize());
    query.with(pageable);
    // 通过strategyId找到对应的数据
    List<StrategyComment> strategyComments = template.find(query, StrategyComment.class);
    return new PageImpl(strategyComments,pageable,totalCount);
  }

}
