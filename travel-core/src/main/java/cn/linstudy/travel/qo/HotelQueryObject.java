package cn.linstudy.travel.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelQueryObject extends QueryObject {
    // 状态
    private Integer status;
}
