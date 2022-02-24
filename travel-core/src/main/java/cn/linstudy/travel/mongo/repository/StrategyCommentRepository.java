package cn.linstudy.travel.mongo.repository;

import cn.linstudy.travel.mongo.domain.StrategyComment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description 攻略评论持久层操作接口
 * 
 * @Date 2021/4/19 13:07
 */
public interface StrategyCommentRepository extends MongoRepository<StrategyComment,String> {// 第一个参数是实体类类型，第二个参数是实体类主键id的类型

}
