package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="下订单")
public class OrderAddObject {
    private Long id;
    private Long roomId;
    private double price;
    private String name; //用户的姓名
    private Integer status; // 1 正常 2 取消
    private String phone; //联系电话
    private String orderSn; // 订单号
    private Long uid; // 购买者
    private Long hotelId; // 酒店的id
    private Long hotelCreater; // 酒店管理者
    private String info; // 订单信息
    private String type; //类型
    private String username; // 购买者姓名
    private String roomName; //房间名字
    private String hotelName; // 名字
    private String idCard; // 名字
}
