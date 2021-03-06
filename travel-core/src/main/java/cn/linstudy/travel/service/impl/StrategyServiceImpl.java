package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.domain.StrategyContent;
import cn.linstudy.travel.domain.StrategyTheme;
import cn.linstudy.travel.mapper.StrategyMapper;
import cn.linstudy.travel.mongo.domain.StrategyComment;
import cn.linstudy.travel.mongo.repository.StrategyCommentRepository;
import cn.linstudy.travel.qo.StrategyQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.StrategyStatisticsRedisService;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.StrategyCatalogService;
import cn.linstudy.travel.service.StrategyContentService;
import cn.linstudy.travel.service.StrategyRankService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.StrategyThemeService;
import cn.linstudy.travel.vo.StrategyConditionVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.elasticsearch.client.watcher.QueuedWatch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * 
 * @Date 2021/4/14 21:50
 */
@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements
    StrategyService {

  @Autowired
  StrategyCatalogService strategyCatalogService;

  @Autowired
  StrategyThemeService strategyThemeService;

  @Autowired
  DestinationService destinationService;

  @Autowired
  StrategyContentService strategyContentService;

  @Autowired
  StrategyRankService strategyRankService;

  @Autowired
  StrategyCommentRepository strategyCommentRepository;

  @Autowired
  RedisTemplate redisTemplate;

  @Override
  public List<Strategy> getRecommend(StrategyQueryObject qo){
    QueryWrapper wrapper = new QueryWrapper();
    if (qo.getKeyword() != null && !(qo.getKeyword().equals(""))) {
      wrapper.like("title", "%" + qo.getKeyword() + "%");
    }

    wrapper.eq(qo.getThemeId() != null, "theme_id", qo.getThemeId());
    List<Strategy> strategyList = super.list(wrapper);
    return strategyList;
  }

  /**
   * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Strategy>
   * @Description: ??????????????????
   * 
   * @date 2021/4/16
   * @Param: [qo]
   */
  @Override
  public Page<Strategy> listForPage(StrategyQueryObject qo) {
    QueryWrapper wrapper = new QueryWrapper();
    Page<Strategy> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
    // ???????????????
    if (qo.getType()!=null){
      // ???????????????????????????????????????????????????id?????????theme_id??????
      if (qo.getType() == 3L){
        wrapper.eq(qo.getRefid()!=null,"theme_id",qo.getRefid());
        // ??????
      }else if (qo.getType() == 2L){
        // ?????????????????????????????????????????????????????????false????????????0
        wrapper.eq("isabroad","0");
        // ???????????????id?????????dest_id??????
        wrapper.eq(qo.getRefid() != null,"dest_id",qo.getRefid());
      }else if (qo.getType() == 1L){
        // ?????????????????????????????????????????????????????????true????????????1
        wrapper.eq("isabroad","1");
        // ???????????????id?????????dest_id??????
        wrapper.eq(qo.getRefid() != null,"dest_id",qo.getRefid());
      }
    }

    if (qo.getKeyword() != null && !(qo.getKeyword().equals(""))) {
      wrapper.like("title", "%" + qo.getKeyword() + "%");
    }

    wrapper.orderByDesc(qo.getOrderBy());
    wrapper.eq(qo.getThemeId() != null, "theme_id", qo.getThemeId());
    return super.page(page, wrapper);
  }

  /**
   * @return cn.linstudy.travel.qo.response.JsonResult
   * @Description: ????????????
   * 
   * @date 2021/4/16
   * @Param: [state, id]
   */
  @Override
  public JsonResult updateState(Integer state, Long id) {
    UpdateWrapper updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", id);
    updateWrapper.set("state", state);
    super.update(updateWrapper);
    return JsonResult.success();
  }

  /**
   * @return cn.linstudy.travel.qo.response.JsonResult
   * @Description: ????????????????????????
   * 
   * @date 2021/4/16
   * @Param: [destId]
   */
  @Override
  public JsonResult viewnumTop3(Long destId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("dest_id", destId);
    queryWrapper.last("limit 3");
    return JsonResult.success(super.list(queryWrapper));
  }

  /**
   * @return cn.linstudy.travel.qo.response.JsonResult
   * @Description: ??????id????????????????????????
   * 
   * @date 2021/4/16
   * @Param: [id]
   */
  @Override
  public JsonResult detail(Long id) {
    Strategy strategy = super.getById(id);
    strategy.setContent(strategyContentService.getById(id));
    return JsonResult.success(strategy);
  }

  /**
   * @return cn.linstudy.travel.qo.response.JsonResult
   * @Description: ????????????
   * 
   * @date 2021/4/16
   * @Param: [type]
   */
  @Override
  public JsonResult rank(Long type) {
    return strategyRankService.rank(type);

  }

  @Override
  public List<Map<String, Object>> condition(Integer type) {
    // ??????
    if (type == 1){

    }
    return null;
  }

  @Override
  public List<Strategy> queryByDestId(Long id) {
    return super.list((Wrapper<Strategy>) new QueryWrapper().eq("dest_id",id));
  }

  @Override
  public List<Strategy> queryByThemeId(Long id) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("theme_id", id);
    return super.list(queryWrapper);
  }

  /**
   * @return boolean
   * @Description: ????????????????????????
   * 
   * @date 2021/4/16
   * @Param: [strategy]
   */
  @Override
  public boolean saveOrUpdate(Strategy strategy) {
    StrategyCatalog catalog = strategyCatalogService.getById(strategy.getCatalogId());

    //?????????id?????????
    strategy.setDestId(catalog.getDestId());
    strategy.setDestName(catalog.getDestName());
    //??????name
    strategy.setCatalogName(catalog.getName());

    //??????name
    StrategyTheme theme = strategyThemeService.getById(strategy.getThemeId());
    strategy.setThemeName(theme.getName());

    //????????????
    List<Destination> toasts = destinationService.getToasts(catalog.getDestId());
    if (toasts != null && toasts.size() > 0) {
      if ("??????".equals(toasts.get(0).getName())) {
        //??????
        strategy.setIsabroad(Strategy.ABROAD_NO);
      } else {
        strategy.setIsabroad(Strategy.ABROAD_YES);
      }
    }
    boolean flag = false;
    StrategyContent content = strategy.getContent();
    //????????????
    if (strategy.getId() != null) {
      //?????????
      flag = super.updateById(strategy);
      //??????
      content.setId(strategy.getId());
      strategyContentService.updateById(content);

    } else {
      //????????????
      strategy.setCreateTime(new Date());
      //????????????????????????0
      strategy.setViewNum(0);
      strategy.setFavorNum(0);
      strategy.setShareNum(0);
      strategy.setThumbsupNum(0);
      strategy.setReplyNum(0);

      flag = this.save(strategy);

      content.setId(strategy.getId());
      strategyContentService.save(content);
    }

    //????????????es?????????
    //????????????????????????runtimeex
    //???????????????????????? ?????????????????????

    return flag;
  }

}
