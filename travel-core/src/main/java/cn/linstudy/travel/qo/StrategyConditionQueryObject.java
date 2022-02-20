package cn.linstudy.travel.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 攻略条件查询QO
 * 
 * @Date 2021/4/16 21:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyConditionQueryObject extends QueryObject{

  private String orderBy ;
  private Long refId;
}
