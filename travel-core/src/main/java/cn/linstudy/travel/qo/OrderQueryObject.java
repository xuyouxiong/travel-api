package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="分页实体类")
public class OrderQueryObject extends QueryObject{
    // 状态
    private Integer state ;
}
