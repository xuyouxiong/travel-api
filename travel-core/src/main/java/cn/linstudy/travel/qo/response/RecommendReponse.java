package cn.linstudy.travel.qo.response;

import cn.linstudy.travel.domain.Strategy;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class RecommendReponse {
    private List<Strategy> records;
    private Integer current;
    private Integer pages;
    private Integer size;
    private Integer total;
}
