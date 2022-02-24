package cn.linstudy.travel.qo;

import cn.linstudy.travel.vo.TravelConditionVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 游记条件查询QO
 * 
 * @Date 2021/4/17 19:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelConditionQueryObject extends QueryObject{

  private String orderBy;

  private Integer travelTimeType;

  private Integer consumeType;

  private Integer dayType;


}
