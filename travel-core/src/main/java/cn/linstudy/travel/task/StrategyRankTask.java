package cn.linstudy.travel.task;

import cn.linstudy.travel.domain.VisitorNum;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.VisitorNumService;
import cn.linstudy.travel.vo.StrategyConditionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 攻略排行的定时任务
 * 
 * @Date 2021/4/16 19:18
 */
//@Component
@Slf4j
public class StrategyRankTask {

  @Autowired
  StrategyService strategyService;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Autowired
  VisitorNumService visitorNumService;
  @Scheduled(cron = "*/5 * * * * *")
  public void doWork(){
      log.info("定时任务开始");
    Set<String> keys = stringRedisTemplate.keys("visitor_num_today*");
    for (String key : keys) {
      String[] split = key.split(":");
      // 拿到登录用户id
      Long ownerId = Long.parseLong(split[1]);
      Long todayNum = Long.parseLong(stringRedisTemplate.opsForValue().get(key)) ;

      // 拿到数据库中的数据
      VisitorNum visitorNumByMySql = visitorNumService
          .getOne(new QueryWrapper<VisitorNum>().eq("owner_id", ownerId));
//      visitorNumByMySql.setTotalVisitorNumber(visitorNumByMySql.getTotalVisitorNumber()+todayNum);
      visitorNumByMySql.setTodayVisitorNumber(todayNum);
      visitorNumService.updateById(visitorNumByMySql);
      log.info("定时任务结束");
    }
  }
}
