package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="房间的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseDomain{
    @ApiModelProperty(value="酒店名称")
    private String name;

    @ApiModelProperty(value = "hotel_id")
    private Long hotelId;

    @ApiModelProperty(value = "image")
    private String image;

    @ApiModelProperty(value = "房间数量")
    private Integer number;

    @ApiModelProperty(value = "规格")
    private String size;

    @ApiModelProperty(value = "房间价格")
    private double price;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @TableField(exist = false)
    private String hotelName;  //游记内容
}
