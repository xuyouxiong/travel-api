package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 攻略表
 * 
 * @Date 2021/4/23 20:23
 */
@Setter
@Getter
@TableName("collection")
public class Collection extends BaseDomain{

  @ApiModelProperty(value="攻略id")
  private Long strategyId;

  @ApiModelProperty(value="用户id")
  private Long userinfoId;

  @ApiModelProperty(value="标签id")
  private Long tagId;

  public Collection(Long strategyId, Long userinfoId, Long tagId) {
    this.strategyId = strategyId;
    this.userinfoId = userinfoId;
    this.tagId = tagId;
  }
}
