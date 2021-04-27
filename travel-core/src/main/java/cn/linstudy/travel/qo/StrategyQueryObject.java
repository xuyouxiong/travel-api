package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/14 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="攻略查询QO")
public class StrategyQueryObject extends QueryObject{

  @ApiModelProperty(value="主题id")
  private Long themeId;

  @ApiModelProperty(value="主题id")
  private Long type = 0L;

  @ApiModelProperty(value="查询条件")
  private String orderBy ="viewnum";

  @ApiModelProperty(value = "地区id")
  private String destid ;

  @ApiModelProperty(value="关联id")
  private Long refid;
}
