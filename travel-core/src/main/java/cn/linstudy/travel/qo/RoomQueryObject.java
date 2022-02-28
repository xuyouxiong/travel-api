package cn.linstudy.travel.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomQueryObject extends QueryObject{
    private Integer status;
}
