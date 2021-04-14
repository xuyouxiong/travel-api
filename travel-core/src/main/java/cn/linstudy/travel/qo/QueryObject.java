package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 分页实体类
 * @Author XiaoLin
 * @Date 2021/4/13 9:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject {

  @ApiModelProperty(value="当前页")
  private int currentPage = 1;
  @ApiModelProperty(value="每页显示条数")
  private int pageSize = 5;
}
