package cn.linstudy.travel.domain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @Description 
 * @Author  XiaoLin
 * @Date  2021/4/11 21:58
 */

@ApiModel(value="地区实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("region")
public class Region extends BaseDomain{

    @ApiModelProperty(value="地区名称")
    private String name;

    @ApiModelProperty(value="简称")
    private String sn;

    @ApiModelProperty(value="是否为热点地区")
    private Integer ishot;

    @ApiModelProperty(value="序号")
    private Integer seq;

    @ApiModelProperty(value="简介")
    private String info;

    @ApiModelProperty(value="关联终点id")
    private String refIds;

    /**
        * @Description: 用于将字符串的ids解析成一个集合
        * 
        * @date 2021/4/14
        * @Param: []
        * @return java.util.List<java.lang.Long>
        */
    public List<Long> parseRefIds(){
        List<Long> ids = new ArrayList<>();
        if(StringUtils.hasLength(refIds)){
            String[] split = refIds.split(",");
            if(split != null && split.length > 0){
                for (int i = 0;i <split.length; i++) {
                    ids.add(Long.parseLong(split[i]));
                }
            }
        }
        return ids;
    }

    /**
        * @Description: 将对象转为JSON字符串
        * 
        * @date 2021/4/14
        * @Param: []
        * @return java.lang.String
        */
    public String getJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("sn",sn);
        map.put("refIds",getRefIds());
        map.put("ishot",ishot);
        map.put("seq",seq);
        map.put("info",info);
        return JSON.toJSONString(map);
    }
}