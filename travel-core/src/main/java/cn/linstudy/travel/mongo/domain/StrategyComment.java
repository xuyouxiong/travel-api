package cn.linstudy.travel.mongo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 攻略评论
 */
@Setter
@Getter
@Document("strategy_comment")
@ToString
public class StrategyComment  {
    @Id
    private String id;
    private Long strategyId;  //攻略(明细)id
    private String strategyTitle; //攻略标题

    private Long userId;    //用户id
    private String nickname;  //用户名
    private String city;
    private int level;
    private String headImgUrl;     //头像


    private Date createTime;    //创建时间
    private String content;      //评论内容


    private int thumbupnum;     //点赞数
    private List<Long> thumbuplist = new ArrayList<>(); // 点赞过该文章的用户id集合
}