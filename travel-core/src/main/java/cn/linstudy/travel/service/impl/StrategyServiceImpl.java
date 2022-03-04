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
   * @Description: 根据条件查询
   * 
   * @date 2021/4/16
   * @Param: [qo]
   */
  @Override
  public Page<Strategy> listForPage(StrategyQueryObject qo) {
    QueryWrapper wrapper = new QueryWrapper();
    Page<Strategy> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
    // 说明是主题
    if (qo.getType()!=null){
      // 如果是主题类型，那么将传过来的关联id赋值给theme_id字段
      if (qo.getType() == 3L){
        wrapper.eq(qo.getRefid()!=null,"theme_id",qo.getRefid());
        // 国内
      }else if (qo.getType() == 2L){
        // 如果是国内，那么需要将是否在国内设置为false，也就是0
        wrapper.eq("isabroad","0");
        // 同时将关联id赋值给dest_id字段
        wrapper.eq(qo.getRefid() != null,"dest_id",qo.getRefid());
      }else if (qo.getType() == 1L){
        // 如果是国外，那么需要将是否在国内设置为true，也就是1
        wrapper.eq("isabroad","1");
        // 同时将关联id赋值给dest_id字段
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
   * @Description: 修改状态
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
   * @Description: 查询出攻略前三名
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
   * @Description: 根据id查询出对应的攻略
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
   * @Description: 攻略排行
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
    // 国外
    if (type == 1){

    }
    return null;
  }

  @Override
  public List<Strategy> queryByDestId(Long id) {
    return super.list((Wrapper<Strategy>) new QueryWrapper().eq("dest_id",id));
  }

  /**
   * @return boolean
   * @Description: 修改或者增加攻略
   * 
   * @date 2021/4/16
   * @Param: [strategy]
   */
  @Override
  public boolean saveOrUpdate(Strategy strategy) {
    StrategyCatalog catalog = strategyCatalogService.getById(strategy.getCatalogId());

    //目的地id与名称
    strategy.setDestId(catalog.getDestId());
    strategy.setDestName(catalog.getDestName());
    //分类name
    strategy.setCatalogName(catalog.getName());

    //主题name
    StrategyTheme theme = strategyThemeService.getById(strategy.getThemeId());
    strategy.setThemeName(theme.getName());

    //是否国外
    List<Destination> toasts = destinationService.getToasts(catalog.getDestId());
    if (toasts != null && toasts.size() > 0) {
      if ("中国".equals(toasts.get(0).getName())) {
        //国内
        strategy.setIsabroad(Strategy.ABROAD_NO);
      } else {
        strategy.setIsabroad(Strategy.ABROAD_YES);
      }
    }
    boolean flag = false;
    StrategyContent content = strategy.getContent();
    //攻略内容
    if (strategy.getId() != null) {
      //攻略：
      flag = super.updateById(strategy);
      //内容
      content.setId(strategy.getId());
      strategyContentService.updateById(content);

    } else {
      //创建时间
      strategy.setCreateTime(new Date());
      //各种统计数默认为0
      strategy.setViewNum(0);
      strategy.setFavorNum(0);
      strategy.setShareNum(0);
      strategy.setThumbsupNum(0);
      strategy.setReplyNum(0);

      flag = this.save(strategy);

      content.setId(strategy.getId());
      strategyContentService.save(content);
    }

    //同步修改es中数据
    //假设操作失败抛出runtimeex
    //触发自定义事件， 让监控程序执行

    return flag;
  }

}
