package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * 
 * @Date 2021/4/13 18:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="目的地的查询QO")
public class DestinationQueryObject extends QueryObject{

  @ApiModelProperty(value="关键字")
  private String keyword;

  @ApiModelProperty(value="父级id")
  private Long parentId;
}
