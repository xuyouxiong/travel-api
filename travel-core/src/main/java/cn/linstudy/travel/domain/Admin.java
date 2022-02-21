package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("admin")
public class Admin extends BaseDomain {
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_SELLER = 2;

    @ApiModelProperty(value="名字/账号")
    private String name;

    @ApiModelProperty(value="密码")
    private String pass;

    @ApiModelProperty(value="手机号码")
    private String phone;

    @ApiModelProperty(value="role")
    private Integer role;

    @ApiModelProperty(value="证书")
    private String cert;

    @ApiModelProperty(value="状态")
    private Integer status;

    @ApiModelProperty(value="法人")
    private String faren;

}
