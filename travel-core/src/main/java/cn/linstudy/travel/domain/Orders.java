package cn.linstudy.travel.domain;

import com.alibaba.fastjson.JSONObject;
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
public class Orders extends BaseDomain {
    @ApiModelProperty(value="住店姓名")
    private String name;

    @ApiModelProperty(value = "roomId")
    private Long roomId;

    @ApiModelProperty(value = "订单号")
    private String orderSn;

    @ApiModelProperty(value = "uid")
    private Long uid;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "价格")
    private double price;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "订单信息")
    private String info;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "酒店名称")
    private String hotelName;

    @ApiModelProperty(value = "房间名称")
    private String roomName;

    @ApiModelProperty(value = "酒店的id")
    private Long hotelId;

    @ApiModelProperty(value = "IDcard")
    private String idCard;

    public String getStatusName() {
        if (this.status == 1) {
            return "已支付";
        } else {
            return "已取消";
        }
    }

    public String getOrderInfo() {
        if (this.type.equals("酒店")) {
            JSONObject jsonObject = JSONObject.parseObject(this.info);
            OrderInfo orderInfo = JSONObject.toJavaObject(jsonObject, OrderInfo.class);
            System.out.println(jsonObject.toString());
            return orderInfo.getHotelName() + "-" + orderInfo.getName();
        } else {
            JSONObject jsonObject = JSONObject.parseObject(this.info);
            OrderInfo orderInfo = JSONObject.toJavaObject(jsonObject, OrderInfo.class);
            return orderInfo.getName();
        }
    }

}
