package cn.linstudy.travel.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @Description 攻略条件统计表
 * @Author XiaoLin
 * @Date 2021/4/16 19:26
 */
@AllArgsConstructor
@Data
@TableName("strategy_condition")
public class StrategyConditionVO {

  public static final Integer TYPE_ABROAD = 1;  //国外

  public static final Integer TYPE_CHINA = 2;   //国内

  public static final Integer TYPE_THEME = 3;     //热门

  private String name;

  private Integer count; //个数

  private Long refid; //关联id

  private Integer type; //条件类型

  private Date statisTime; //归档统计时间


}
