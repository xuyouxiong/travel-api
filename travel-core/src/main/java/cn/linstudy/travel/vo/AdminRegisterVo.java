package cn.linstudy.travel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AdminRegisterVo {
    @ApiModelProperty(value = "姓名/账号")
    private String name;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "证书")
    private String cert;

    @ApiModelProperty(value = "法人")
    private String faren;

}
