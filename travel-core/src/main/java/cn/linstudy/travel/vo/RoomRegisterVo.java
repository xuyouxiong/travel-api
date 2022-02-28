package cn.linstudy.travel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomRegisterVo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "房间名称")
    private String name;

    @ApiModelProperty(value = "房间图片")
    private String image;

    @ApiModelProperty(value = "房间数量")
    private Integer number;

    @ApiModelProperty(value = "房间价格")
    private double price;

    @ApiModelProperty(value = "规格")
    private String size;

    @ApiModelProperty(value = "hotel_id")
    private Long hotel_id;
}
