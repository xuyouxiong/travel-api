package cn.linstudy.travel.service;

import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.vo.StrategyConditionVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/16 19:52
 */
public interface StrategyConditionService extends IService<StrategyConditionVO> {

  /**
      * @Description: 旅游攻略查询
      * @author XiaoLin
      * @date 2021/4/16
      * @Param: [type]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult condition(Integer type);
}
