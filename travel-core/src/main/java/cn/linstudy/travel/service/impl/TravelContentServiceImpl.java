package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.TravelContent;
import cn.linstudy.travel.mapper.TravelContentMapper;
import cn.linstudy.travel.qo.TravelConditionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.TravelContentService;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.vo.TravelConditionVO;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * @Description 游记内容业务实现类
 * 
 * @Date 2021/4/17 16:07
 */
@Service
public class TravelContentServiceImpl extends ServiceImpl<TravelContentMapper, TravelContent> implements
    TravelContentService {

  @Autowired
  TravelService travelService;

  @Autowired
  TravelContentService travelContentService;

  @Autowired
  UserInfoService userInfoService;

  /**
      * @Description: 通过id查询内容
      * 
      * @date 2021/4/17
      * @Param: [id]
      * @return cn.linstudy.travel.domain.TravelContent
      */
  @Override
  public TravelContent getContentById(Long id) {
    return super.getById(id);
  }

  /**
      * @Description: 内容审核
      * 
      * @date 2021/4/17
      * @Param: [id, state]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult audit(Long id, Integer state) {
    // 审核不通过
    if (state == Travel.STATE_REJECT){
      // 将状态改为拒绝通过
      travelService.updateById(travelService.getById(id).setState(Travel.STATE_REJECT));
      // 审核通过
    }else if (state == Travel.STATE_RELEASE){
       //修改状态为审核通过状态。
      Travel travel = travelService.getById(id);
      travelService.updateById(travel.setState(Travel.STATE_RELEASE));
       //设置发布时间。
      travel.setReleaseTime(new Date());
       //最后更新时间。
      travel.setReleaseTime(new Date());
       //记录审核信息（审核人、审核时间、审核状态、审核备注，审核i信息的id）
      travelService.updateById(travel);
    } else if (state == -1) {
      Travel travel = travelService.getById(id);
      travelService.updateById(travel.setState(-1));
      //设置发布时间。
      travel.setReleaseTime(new Date());
      //最后更新时间。
      travel.setReleaseTime(new Date());
      //记录审核信息（审核人、审核时间、审核状态、审核备注，审核i信息的id）
      travelService.updateById(travel);
    }
      return JsonResult.success();
  }

  @Override
  public JsonResult queryByDestId(TravelConditionQueryObject qo,Long destId) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq(destId != null, "dest_id", destId);
    // 如果指定排序规则
    if (qo.getOrderBy() != null){
      queryWrapper.orderByDesc(qo.getOrderBy());
    }
    // 如果指定出行时间
    if (qo.getTravelTimeType() != null && qo.getTravelTimeType() != -1){
      queryWrapper.ge("month(travel_time)",TravelConditionVO.TIME_TYPE.get(qo.getTravelTimeType()).getMin());
      queryWrapper.le("month(travel_time)",TravelConditionVO.TIME_TYPE.get(qo.getTravelTimeType()).getMax());
    }

    // 如果指定了人均消费
    if (qo.getConsumeType() != null && qo.getConsumeType() != -1){
      queryWrapper.ge("avg_consume",TravelConditionVO.MONEY_TYPE.get(qo.getConsumeType()).getMin());
      Integer max = TravelConditionVO.MONEY_TYPE.get(qo.getConsumeType()).getMax();
      if (max != null){
      queryWrapper.le("avg_consume",max);
      }
    }

    // 如果指定了出行天数
    if (qo.getDayType() != null && qo.getDayType() != -1){
      queryWrapper.ge("day",TravelConditionVO.DAY_TYPE.get(qo.getDayType()).getMin());
      Integer max = TravelConditionVO.DAY_TYPE.get(qo.getDayType()).getMax();
      if (max != null){
        queryWrapper.le("day",max);
      }
    }

    // 如果有关键词的模糊搜索
    if (qo.getKeyword() != null && !(qo.getKeyword().equals(""))) {
      queryWrapper.like("title", "%" + qo.getKeyword() + "%");
    }

    Page<Travel> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
     travelService.page(page,queryWrapper);
    for (Travel travel : page.getRecords()) {
     travel.setAuthor(userInfoService.getById(travel.getAuthorId()));
    }
    return JsonResult.success(page);
  }

  @Override
  public JsonResult detail(Long destId,Long id) {
    Travel travel = travelService.getById(id);
    travel.setAuthor(userInfoService.getById(travel.getAuthorId()));
    travel.setContent(travelContentService.getById(id));
    return JsonResult.success(travel);
  }
}
