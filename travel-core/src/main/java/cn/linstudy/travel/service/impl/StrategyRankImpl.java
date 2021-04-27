package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyRank;
import cn.linstudy.travel.mapper.StrategyRankMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.StrategyRankService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/16 14:09
 */
@Service
public class StrategyRankImpl extends ServiceImpl<StrategyRankMapper, StrategyRank> implements
    StrategyRankService {

  @Override
  public JsonResult rank(Long type) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.orderByDesc("statisnum");
    queryWrapper.eq("type",type);
    queryWrapper.last("limit 10");
    queryWrapper.inSql("statis_time","select max(statis_time) from strategy_rank");
    return JsonResult.success(super.list(queryWrapper));
  }
}
