package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Description
 * 
 */
@Setter
@Getter
@TableName("visitor")
@Accessors(chain = true)
@ApiModel(value = "cn.linstudy.travel.domain",description = "访客信息表")
public class Visitor extends BaseDomain{
  private Long visitorId;
  private Long ownerId;

  @TableField(exist = false)
  private UserInfo visitor;

  public Visitor(Long visitorId, Long ownerId) {
    this.visitorId = visitorId;
    this.ownerId = ownerId;
  }
}
