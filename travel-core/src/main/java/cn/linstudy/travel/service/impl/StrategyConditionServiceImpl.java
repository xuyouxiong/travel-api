package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.mapper.StrategyConditionMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.StrategyConditionService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.vo.StrategyConditionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/16 19:54
 */
@Service
public class StrategyConditionServiceImpl extends ServiceImpl<StrategyConditionMapper, StrategyConditionVO> implements
    StrategyConditionService {

  @Override
  public JsonResult condition(Integer type) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("type",type);
    queryWrapper.inSql("statis_time","select max(statis_time) from strategy_condition");
    return JsonResult.success(super.list(queryWrapper));
  }
}
