package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
    * @Description: 用于抽取所有的实体类id
    * 
    * @date 2021/4/9
    */
@Data
@Accessors(chain = true)
public abstract class BaseDomain implements Serializable {
    // MyBatis-Plus表示这个是id自增
    @ApiModelProperty(value = "主键id",example="1")
    @TableId(type = IdType.AUTO)
    protected Long id;
}
