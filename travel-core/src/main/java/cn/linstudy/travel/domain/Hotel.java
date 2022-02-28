package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="酒店的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseDomain{
    @ApiModelProperty(value="酒店名称")
    private String name;

    @ApiModelProperty(value = "uid")
    private Long uid;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "最低价格")
    private double lowPrice;

    @ApiModelProperty(value = "酒店封面图")
    private String image;

    @ApiModelProperty(value = "酒店介绍")
    private String summary;

    @ApiModelProperty(value = "开始营业时间")
    private String startDate;

    @ApiModelProperty(value = "结束营业时间")
    private String endDate;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @TableField(exist = false)
    private String username;  //游记内容
}
