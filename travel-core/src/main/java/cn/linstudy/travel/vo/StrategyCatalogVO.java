package cn.linstudy.travel.vo;

import cn.linstudy.travel.domain.StrategyCatalog;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 攻略主题VO
 * 
 * @Date 2021/4/15 11:30
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StrategyCatalogVO {

  @ApiModelProperty(value = "名称")
  private String destName;

  @ApiModelProperty(value = "攻略主题对象")
  private List<StrategyCatalog> catalog;
}
