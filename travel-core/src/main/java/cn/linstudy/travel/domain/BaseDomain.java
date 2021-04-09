package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
/**
    * @Description: 用于抽取所有的实体类id
    * @author XiaoLin
    * @date 2021/4/9
    */
public abstract class BaseDomain implements Serializable {
    // MyBatis-Plus表示这个是id自增
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    protected Long id;
}
