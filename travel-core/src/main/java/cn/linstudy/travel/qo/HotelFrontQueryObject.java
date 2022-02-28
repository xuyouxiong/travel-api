package cn.linstudy.travel.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelFrontQueryObject extends QueryObject {
    private String name;
    private String startDate;
    private String endDate;
}
