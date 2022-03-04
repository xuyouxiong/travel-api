package cn.linstudy.travel.redis.service.impl;
import cn.linstudy.travel.domain.Collection;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.StrategyStatisticsRedisService;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import cn.linstudy.travel.service.CollectionService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @Description
 * 
 * @Date 2021/4/19 19:48
 */
@Service
public class StrategyStatisticsRedisServiceImpl implements StrategyStatisticsRedisService {

  @Autowired
  StringRedisTemplate redisTemplate;

  @Autowired
  StrategyService strategyService;

  @Autowired
  CollectionService collectionService;

  @Override
  public void setStrategyStatisticsVO(StrategyStatisticsVO vo) {
    //拼接vo key
    String key = RedisKeyEnum.STRATEGY_STATISTICS_VO.join(String.valueOf(vo.getStrategyId()));
    redisTemplate.opsForValue().set(key,JSON.toJSONString(vo));
  }

  @Override
  public JsonResult strategyThumbup(Long strategyId, Long userId) {
    String key = RedisKeyEnum.STRATEGY_THUMBUP.join(strategyId.toString(), userId.toString());
    boolean flag = false;
    if (!redisTemplate.hasKey(key)){
      // 如果不存在说明可以顶
      StrategyStatisticsVO strategyStatisticsVO = getStrategyStatisticsVO(strategyId);
      strategyStatisticsVO.setThumbsupNum(strategyStatisticsVO.getThumbsupNum()+1);
      long time = DateUtil.getDateBetween(new Date(), DateUtil.getEndDate(new Date()));
      redisTemplate.opsForValue().set(key,"1",time, TimeUnit.SECONDS);
      setStrategyStatisticsVO(strategyStatisticsVO);
      flag = true;
    }
      // 如果存在，什么都不干
    return JsonResult.success(flag);
  }

  /**
      * @Description: 获取VO对象方法封装
      * 
      * @date 2021/4/20
      * @Param: [strategyId]
      * @return cn.linstudy.travel.redis.vo.StrategyStatisticsVO
      */
  @Override
  public StrategyStatisticsVO getStrategyStatisticsVO(Long strategyId) {
    //拼接vo key
    String key = RedisKeyEnum.STRATEGY_STATISTICS_VO.join(String.valueOf(strategyId));
    StrategyStatisticsVO vo =null;
    //判断key是否存在
    if (redisTemplate.hasKey(key)){
      String token = redisTemplate.opsForValue().get(key).toString();
      vo = JSON.parseObject(token, StrategyStatisticsVO.class);
    }else {
      //创建vo对象
      vo=new StrategyStatisticsVO();
      //查询mysql数据库,将攻略里面所有统计数据,初始化到vo对象中
      Strategy strategy = strategyService.getById(strategyId);
      BeanUtils.copyProperties(strategy,vo);
      vo.setStrategyId(strategy.getId());
      //缓存到redis中
      redisTemplate.opsForValue().set(key,JSON.toJSONString(vo));
    }
    return vo;
  }

  /**
      * @Description: 阅读数自增
      * 
      * @date 2021/4/20
      * @Param: [strategyId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult increaseViewNum( Long strategyId) {
    StrategyStatisticsVO vo = this.getStrategyStatisticsVO(strategyId);
    //将vo对象viewnum属性执行+1操作
    vo.setViewNum(vo.getViewNum()+1);
    this.setStrategyStatisticsVO(vo);
    return JsonResult.success(vo);
  }

  /**
      * @Description: 收藏攻略
      * 
      * @date 2021/4/23
      * @Param: [strategyId 攻略id, sid 用户id]
      * @return boolean
      */
  @Override
  public boolean favorStrategy(Long strategyId, Long sid) {
    // 判断用户是否登录
    if (sid == null || !StringUtils.hasLength(sid.toString())){
      throw  new LogicException("请先登录");
    }
    List<Long> favorIds = null;
    // 如果登录了，就拼接key
    String key = RedisKeyEnum.STRATEGY_FAVOR.join(sid.toString());
    // 判断key是否存在，如果存在的话说明当前请求为取消收藏请求
    if (redisTemplate.hasKey(key)) {
      // 存在说明为取消收藏请求,拿到redis中的1value
      String favorNum = redisTemplate.opsForValue().get(key);
      // 转为list集合
      favorIds = JSON.parseArray(favorNum, Long.class);
    }else {
      // 如果不存在说明为收藏请求
      favorIds = new ArrayList<>();
    }
    boolean flag = false;
    StrategyStatisticsVO strategyStatisticsVO = this.getStrategyStatisticsVO(strategyId);
    // 判断集合中是否存在，如果存在说明为取消收藏，返回false
    String currentKey = RedisKeyEnum.COLLECTION_STRATEGY.join(sid.toString());
    List<Long> strategyList = JSON.parseArray(redisTemplate.opsForValue().get(currentKey),Long.class);
    if (favorIds.contains(strategyId)){
      // 收藏数-1
      strategyStatisticsVO.setFavorNum(strategyStatisticsVO.getFavorNum()-1);
      // 将攻略id移除redis中
      favorIds.remove(strategyId);

      strategyList.remove(strategyId);
      redisTemplate.opsForValue().set(currentKey,JSON.toJSONString(strategyList));
      collectionService.remove(new QueryWrapper<Collection>().eq("userinfo_id",sid).eq("strategy_id",strategyId));
    }else {
      strategyStatisticsVO.setFavorNum(strategyStatisticsVO.getFavorNum()+1);
      Strategy strategy = strategyService.getById(strategyId);
      collectionService.save(new Collection(strategyId,sid, strategy.getThemeId()));
      favorIds.add(strategyId);
      strategyList.add(strategyId);
      redisTemplate.opsForValue().set(currentKey,JSON.toJSONString(strategyList));
      flag = true;
    }
    // 重新赋值vo
    redisTemplate.opsForValue().set(key,JSON.toJSONString(favorIds));
    this.setStrategyStatisticsVO(strategyStatisticsVO);
    return flag;
  }


}
