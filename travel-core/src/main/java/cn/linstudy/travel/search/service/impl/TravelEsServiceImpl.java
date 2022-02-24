package cn.linstudy.travel.search.service.impl;


import cn.linstudy.travel.search.domain.TravelEs;
import cn.linstudy.travel.search.repository.TravelEsRepository;
import cn.linstudy.travel.search.service.TravelEsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelEsServiceImpl implements TravelEsService {

    @Autowired
    private TravelEsRepository repository;

    @Override
    public void save(TravelEs travelEsEs) {
        repository.save(travelEsEs);
    }

    @Override
    public void update(TravelEs travelEsEs) {
        repository.save(travelEsEs);
    }

    @Override
    public TravelEs get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<TravelEs> list() {
        List<TravelEs> list = new ArrayList<>();
        Iterable<TravelEs> all = repository.findAll();
        all.forEach(a -> list.add(a));
        return list;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
