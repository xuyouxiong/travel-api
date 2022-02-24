package cn.linstudy.travel.search.repository;


import cn.linstudy.travel.search.domain.TravelEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TravelEsRepository extends ElasticsearchRepository<TravelEs, String> {
}
