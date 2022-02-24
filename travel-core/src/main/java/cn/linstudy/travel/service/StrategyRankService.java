package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyRank;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Description 攻略排行的业务类接口
 *
 * @Date 2021/4/16 14:07
 */
public interface StrategyRankService extends IService<StrategyRank> {

  JsonResult rank(Long type);
}
