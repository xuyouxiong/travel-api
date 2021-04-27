package cn.linstudy.travel.mongo.repository;


import cn.linstudy.travel.mongo.domain.TravelComment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description 游记评论持久层操作接口
 * @Author XiaoLin
 * @Date 2021/4/19 13:07
 */
public interface TravelCommentRepository extends MongoRepository<TravelComment, String> {
  /**
   * 通过游记id查询游记评论集合
   * @param travelId
   * @return
   */
  List<TravelComment> findByTravelId(Long travelId);
}
