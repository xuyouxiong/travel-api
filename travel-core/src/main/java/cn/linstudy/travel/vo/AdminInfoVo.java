package cn.linstudy.travel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminInfoVo {
    @ApiModelProperty(value = "姓名/账号")
    private String name;

    @ApiModelProperty(value = "密码")
    private String pass;
}
