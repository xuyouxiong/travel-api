package cn.linstudy.travel.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("travel")
public class TravelVo {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private Integer state;

    @ApiModelProperty(value = "密码")
    private String title;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "出发时间")
    private String travelTime;

    @ApiModelProperty(value = "人物")
    private Integer person;

    @ApiModelProperty(value = "是否公开")
    private Integer ispublic;

    @ApiModelProperty(value = "出行天数")
    private Integer day;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "旅游地点")
    private Long destId;

    @ApiModelProperty(value = "人均RMB")
    private Integer perExpend;
}
