package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="订单的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseDomain {
    @ApiModelProperty(value="住店姓名")
    private String name;

    @ApiModelProperty(value = "roomId")
    private Long roomId;

    @ApiModelProperty(value = "订单号")
    private String ordersn;

    @ApiModelProperty(value = "uid")
    private Long uid;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "价格")
    private double price;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
