package cn.linstudy.travel.search.repository;


import cn.linstudy.travel.search.domain.UserInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserInfoEsRepository extends ElasticsearchRepository<UserInfoEs, String> {
}
