package cn.linstudy.travel.mapper;

import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.StrategyRank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 攻略排行的接口
 * 
 * @Date 2021/4/16 14:05
 */
public interface StrategyRankMapper extends BaseMapper<StrategyRank> {

    @Select("SELECT strategy.* from strategy_rank as rank left join strategy on rank.strategy_id = strategy.id order by rank.statisnum desc limit 3")
    List<Strategy> getStrategys();
}
