package cn.linstudy.travel.mongo.service.impl;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.mongo.domain.TravelComment;
import cn.linstudy.travel.mongo.repository.TravelCommentRepository;
import cn.linstudy.travel.mongo.service.TravelCommentService;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description
 * 
 * @Date 2021/4/19 21:56
 */
@Service
public class TravelCommentServiceImpl implements TravelCommentService {

  @Autowired
  UserInfoRedisService userInfoRedisService;

  @Autowired
  TravelCommentRepository travelCommentRepository;

  @Autowired
  MongoTemplate template;
  @Override
  public JsonResult commentAdd(TravelComment travelComment, HttpServletRequest httpServletRequest) {
    travelComment.setId(null);
    travelComment.setCreateTime(new Date());
    // 维护评论的评论
    String refId = travelComment.getRefComment().getId();
    if (StringUtils.hasLength(refId)){
      // 如果有值那就是评论的评论
      Optional<TravelComment> parentComment = travelCommentRepository.findById(refId);
      if (parentComment.isPresent()){
        // 是评论的评论
        travelComment.setRefComment(parentComment.get());
      }
      travelComment.setType(TravelComment.TRAVLE_COMMENT_TYPE);
    }else {
      // 普通评论
      travelComment.setType(TravelComment.TRAVLE_COMMENT_TYPE_COMMENT);
    }

    UserInfo userInfo = userInfoRedisService.getUserInfoByToken(httpServletRequest.getHeader("token"));
    BeanUtils.copyProperties(userInfo,travelComment);
    travelComment.setUserId(userInfo.getId());
    travelCommentRepository.save(travelComment);
    return JsonResult.success();
  }

  @Override
  public List<TravelComment> queryByTravelId(Long travelId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("travelId").is(travelId));
    List<TravelComment> travelComments = template.find(query, TravelComment.class);
    return travelComments;
  }
}
