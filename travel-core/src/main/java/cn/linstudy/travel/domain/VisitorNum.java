package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author XiaoLin
 */
@Setter
@Getter
@TableName("visitor_num")
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "cn.linstudy.travel.domain",description = "访客数量表")
public class VisitorNum extends BaseDomain{

  private Long ownerId;
  private Long todayVisitorNumber;
  private Long totalVisitorNumber;

  public VisitorNum(Long ownerId, Long todayVisitorNumber, Long totalVisitorNumber) {
    this.ownerId = ownerId;
    this.todayVisitorNumber = todayVisitorNumber;
    this.totalVisitorNumber = totalVisitorNumber;
  }
}
