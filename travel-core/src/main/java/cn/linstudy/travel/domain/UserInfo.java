package cn.linstudy.travel.domain;
import cn.linstudy.travel.constant.SystemConstant;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * @Description 用户信息实体类
 * 
 * @Date 2021/4/9 14:18
 */
@Setter
@Getter
@TableName("userinfo")
@ApiModel(value = "cn.linstudy.travel.domain",description = "用户信息实体类")
public class UserInfo extends BaseDomain{

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String email;  //邮箱

    @JsonIgnore
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别")
    private Integer gender = SystemConstant.GENDER_SECRET;

    @ApiModelProperty(value = "用户级别")
    private Integer level = 0;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "个性签名")
    private String info;

    @ApiModelProperty(value = "状态")
    private Integer state = SystemConstant.STATE_NORMAL;

    public UserInfo(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}