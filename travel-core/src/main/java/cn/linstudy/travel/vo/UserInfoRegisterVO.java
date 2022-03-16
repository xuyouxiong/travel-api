package cn.linstudy.travel.vo;

import cn.linstudy.travel.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户注册提交表单的VO
 * 
 * @Date 2021/4/10 9:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userinfo")
public class UserInfoRegisterVO extends BaseDomain {

  @ApiModelProperty(value = "昵称")
  private String nickname;

  @ApiModelProperty(value = "密码")
  private String password;

  @TableField(exist = false)
  @ApiModelProperty(value = "手机验证码")
  private String verifyCode;

  // 这个字段不会映射到数据库中
  @TableField(exist = false)
  @ApiModelProperty(value = "确认密码")
  private String repeatPassword;

  @ApiModelProperty(value = "手机")
  private String phone;
}
