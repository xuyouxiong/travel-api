package cn.linstudy.travel.redis.service;

import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.vo.StrategyStatisticsVO;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description 攻略统计Redis相关接口
 * @Author XiaoLin
 * @Date 2021/4/19 19:48
 */
public interface StrategyStatisticsRedisService {

  /**
      * @Description: 阅读数自增
      * @author XiaoLin
      * @date 2021/4/19
      * @Param: [strategyId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult increaseViewNum(Long strategyId)
      throws InvocationTargetException, IllegalAccessException;

  /**
      * @Description: 收藏攻略
      * @author XiaoLin
      * @date 2021/4/20
      * @Param: [strategyId, id]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  boolean favorStrategy(Long strategyId, Long sid);

  /**
      * @Description: 获取统计VO对象
      * @author XiaoLin
      * @date 2021/4/20
      * @Param: []
      * @return cn.linstudy.travel.redis.vo.StrategyStatisticsVO
      */
  StrategyStatisticsVO getStrategyStatisticsVO(Long strategyId);

  /**
      * @Description: 设置VO对象到Redis
      * @author XiaoLin
      * @date 2021/4/20
      * @Param: [strategyId]
      * @return void
      */
  void setStrategyStatisticsVO(StrategyStatisticsVO vo);

  JsonResult strategyThumbup(Long strategyId, Long id);
}
