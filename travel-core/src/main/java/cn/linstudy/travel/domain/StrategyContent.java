package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 攻略内容
 */
@ApiModel(value="攻略内容实体类")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TableName("strategy_content")
public class StrategyContent implements Serializable {

    @ApiModelProperty(value="id")
    private Long id;

    @ApiModelProperty(value="内容")
    private String content;
}

