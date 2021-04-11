package cn.linstudy.travel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/10 21:16
 */
@Data
public class UserInfoLoginVO {

  @ApiModelProperty(value = "手机")
  private String phone;

  @ApiModelProperty(value = "密码")
  private String password;
}
