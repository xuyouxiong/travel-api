package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="景点门票")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("spot")
public class Spot extends BaseDomain{
    @ApiModelProperty(value="景点名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private double price;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "uid")
    private Long uid;

    @ApiModelProperty(value = "封面")
    private String image;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "摘要")
    private String summary;
}
