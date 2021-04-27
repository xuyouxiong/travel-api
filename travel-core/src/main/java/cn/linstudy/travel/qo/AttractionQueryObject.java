package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 景点的查询QO
 * @Author XiaoLin
 * @Date 2021/4/14 18:43
 */
@ApiModel(value="景点的查询QO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttractionQueryObject extends QueryObject{

  @ApiModelProperty(value="查询详情的id")
  private Long id;
}
