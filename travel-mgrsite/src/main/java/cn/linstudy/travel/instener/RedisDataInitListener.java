package cn.linstudy.travel.instener;


import cn.linstudy.travel.domain.Collection;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import cn.linstudy.travel.service.CollectionService;
import cn.linstudy.travel.service.StrategyService;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description Redis数据初始化监听器，将mysql中的数据同步到redis
 *
 * @Date 2021/4/20 15:57
 */
@Slf4j
@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  StrategyService strategyService;

  @Autowired
  StringRedisTemplate redisTemplate;

  @Autowired
  CollectionService collectionService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

//    initStrategy();
    initCollection();

  }

  public void initCollection(){
    log.info("===========收藏信息初始化开始===========");
    List<Collection> list = collectionService.list();
    Map<Long, List<Long>> collectionListMap = new HashMap<>();
    if (list.size()>0){
      for (Collection collection : list) {
        // 获取用户id
        Long userinfoId = collection.getUserinfoId();
        // 看这个map的list中是否含有改用户
        if (collectionListMap.containsKey(userinfoId)) {
          // 如果有就在尾部继续添加该用户收藏的攻略
          collectionListMap.get(userinfoId).add(collection.getStrategyId());
        }else {
          // 如果没有就新增一个list用户存储用户收藏的攻略id
          collectionListMap = new HashMap<>();
          ArrayList<Long> strategyList = new ArrayList<>();
          strategyList.add(collection.getStrategyId());
          collectionListMap.put(collection.getUserinfoId(),strategyList);
        }
        // 拼接key： 用户id :收藏的攻略id
        String key = RedisKeyEnum.COLLECTION_STRATEGY.join(collection.getUserinfoId().toString());
        redisTemplate.opsForValue().set(key,JSON.toJSONString(collectionListMap.get(collection.getUserinfoId())));
      }
    }
    log.info("===========收藏信息初始化结束===========");
  }

  public void initStrategy(){
    log.info("===========攻略信息初始化开始===========");
    // 查询出所有的攻略信息
    List<Strategy> strategyList = strategyService.list();
    for (Strategy strategy : strategyList) {
      String key = RedisKeyEnum.STRATEGY_STATISTICS_VO.join(strategy.getId().toString());
      if (!redisTemplate.hasKey(key)){
        StrategyStatisticsVO strategyStatisticsVO = new StrategyStatisticsVO();
        BeanUtils.copyProperties(strategy,strategyStatisticsVO);
        strategyStatisticsVO.setStrategyId(strategy.getId());
        redisTemplate.opsForValue().set(key, JSON.toJSONString(strategyStatisticsVO));

      }else {
        continue;
      }

    }
    log.info("===========初始化结束===========");
  }
}
