package cn.linstudy.travel.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 游记查询VO
 *
 * @Date 2021/4/17 14:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelQueryObject extends QueryObject{

  // 状态
  private Integer state ;
}
