package cn.linstudy.travel.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description  景点实体类
 * @Author  XiaoLin
 * @Date  2021/4/14 18:34
 */

/**
    * 景点表
    */
@ApiModel(value="景点实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attraction {
    @ApiModelProperty(value="id主键")
    private Long id;

    @ApiModelProperty(value="多少人去过")
    private Long went;

    @ApiModelProperty(value="费时参考")
    private Integer timeCost;

    @ApiModelProperty(value="景点名字")
    private String name;

    @ApiModelProperty(value="景点图片")
    private String image;

    @ApiModelProperty(value="电话")
    private String phone;

    @ApiModelProperty(value="梗概")
    private String summary;

    @ApiModelProperty(value="网址")
    private String url;

    @ApiModelProperty(value="内容")
    private String  content;

}