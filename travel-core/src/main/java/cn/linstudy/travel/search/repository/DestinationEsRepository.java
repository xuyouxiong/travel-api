package cn.linstudy.travel.search.repository;


import cn.linstudy.travel.search.domain.DestinationEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DestinationEsRepository extends ElasticsearchRepository<DestinationEs, String>{
}
