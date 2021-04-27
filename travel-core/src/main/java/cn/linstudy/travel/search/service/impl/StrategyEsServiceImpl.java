package cn.linstudy.travel.search.service.impl;


import cn.linstudy.travel.search.domain.StrategyEs;
import cn.linstudy.travel.search.repository.StrategyEsRepository;
import cn.linstudy.travel.search.service.StrategyEsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyEsServiceImpl implements StrategyEsService {

    @Autowired
    private StrategyEsRepository repository;

    @Override
    public void save(StrategyEs strategyEsEs) {
        //strategyEsEs.setId(null);
        repository.save(strategyEsEs);
    }

    @Override
    public void update(StrategyEs strategyEsEs) {
        repository.save(strategyEsEs);
    }

    @Override
    public StrategyEs get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<StrategyEs> list() {
        List<StrategyEs> list = new ArrayList<>();
        Iterable<StrategyEs> all = repository.findAll();
        all.forEach(a -> list.add(a));
        return list;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
