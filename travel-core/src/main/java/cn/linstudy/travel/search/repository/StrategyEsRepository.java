package cn.linstudy.travel.search.repository;


import cn.linstudy.travel.search.domain.StrategyEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StrategyEsRepository extends ElasticsearchRepository<StrategyEs, String> {
}
