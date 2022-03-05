package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.qo.StrategyQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2021/4/14 21:48
 */
public interface StrategyService extends IService<Strategy> {

  /**
      * @Description: 攻略的分页显示
      * @date 2021/4/16
      * @Param: [qo]
      * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Strategy>
      */
  Page<Strategy> listForPage(StrategyQueryObject qo);


  List<Strategy> getRecommend(StrategyQueryObject qo);

  /**
      * @Description: 修改攻略状态
      * 
      * @date 2021/4/16
      * @Param: [state, id]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult updateState(Integer state, Long id);

  /**
      * @Description: 查看前三攻略
      * 
      * @date 2021/4/16
      * @Param: [destId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult viewnumTop3(Long destId);

  /**
      * @Description: 根据id攻略
      * 
      * @date 2021/4/16
      * @Param: [id]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult detail(Long id);

  /**
      * @Description: 排行榜业务类
      * @date 2021/4/16
      * @Param: [type]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult rank(Long type);

  List<Map<String ,Object>> condition(Integer type);

  List<Strategy> queryByDestId(Long id);

  List<Strategy> queryByThemeId(Long id);

}
