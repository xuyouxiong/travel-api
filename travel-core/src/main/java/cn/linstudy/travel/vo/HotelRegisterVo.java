package cn.linstudy.travel.vo;

import cn.linstudy.travel.domain.StrategyCatalog;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HotelRegisterVo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "酒店名称")
    private String name;

    @ApiModelProperty(value = "酒店地址")
    private String address;

    @ApiModelProperty(value = "最低价格")
    private double low_price;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "介绍")
    private String summary;

    @ApiModelProperty(value = "uid")
    private Long uid;

    @ApiModelProperty(value = "startDate")
    private String startDate;

    @ApiModelProperty(value = "endDate")
    private String endDate;
}
