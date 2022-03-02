package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tags")
public class Tags extends BaseDomain{
    @ApiModelProperty(value="tag的名称")
    private String name;

    @ApiModelProperty(value="外键")
    private Long themeId;
}
