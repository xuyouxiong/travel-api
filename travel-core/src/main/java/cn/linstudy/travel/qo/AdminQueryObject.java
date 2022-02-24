package cn.linstudy.travel.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="商家的查询")
public class AdminQueryObject extends QueryObject{
    @ApiModelProperty(value="关键字")
    private String keyword;


}
