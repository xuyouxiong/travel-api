package cn.linstudy.travel.task;

import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.vo.StrategyConditionVO;
import com.alibaba.fastjson.JSON;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description
 * 
 * @Date 2021/4/21 11:14
 */
//@Component
@Slf4j
public class RedisDataToMySqlTask {

  @Autowired
  StringRedisTemplate redisTemplate;

  @Autowired
  StrategyService strategyService;

  @Scheduled(cron = "*/5 * * * * *")
  public void doWork(){
    log.info("定时任务开始");
    Set<String> keys = redisTemplate.keys("strategy_statis_vo*");
    for (String key : keys) {
      StrategyStatisticsVO strategyStatisticsVO = JSON.parseObject(redisTemplate.opsForValue().get(key),StrategyStatisticsVO.class);
      Strategy strategy = new Strategy();
      BeanUtils.copyProperties(strategyStatisticsVO,strategy);
      strategy.setId(strategyStatisticsVO.getStrategyId());

      strategyService.updateById(strategy);
    }
  }
}
