package cn.linstudy.travel.redis.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 攻略redis中统计数据
 * 运用模块：
 *  1：数据统计(回复，点赞，收藏，分享，查看)
 */

@Getter
@Setter

public class StrategyStatisticsVO implements Serializable {

    private Long strategyId;  //攻略id

    private int viewNum;  //点击数

    private int replyNum;  //攻略评论数

    private int favorNum; //收藏数

    private int shareNum; //分享数

    private int thumbsupNum; //点赞个数

}