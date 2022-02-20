package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 攻略评论QO
 * 
 * @Date 2021/4/19 19:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="攻略评论实体类")
public class StrategyCommentQueryObject extends QueryObject{

  @ApiModelProperty(value="攻略id")
  private Long strategyId;
}
