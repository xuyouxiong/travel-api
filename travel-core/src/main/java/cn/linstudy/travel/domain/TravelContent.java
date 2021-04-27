package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 游记内容
 */
@ApiModel(value="游记内容实体类")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TableName("travel_content")
public class TravelContent implements Serializable {

    @ApiModelProperty(value="id")
    private Long id;

    @ApiModelProperty(value="内容")
    private String content;
}

