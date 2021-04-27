package cn.linstudy.travel.search.service.impl;


import cn.linstudy.travel.search.domain.DestinationEs;
import cn.linstudy.travel.search.repository.DestinationEsRepository;
import cn.linstudy.travel.search.service.DestinationEsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationEsServiceImpl implements DestinationEsService {

    @Autowired
    private DestinationEsRepository repository;

    @Override
    public void save(DestinationEs destinationEsEs) {
        //destinationEsEs.setId(null);
        repository.save(destinationEsEs);
    }

    @Override
    public void update(DestinationEs destinationEsEs) {
        repository.save(destinationEsEs);
    }

    @Override
    public DestinationEs get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DestinationEs> list() {
        List<DestinationEs> list = new ArrayList<>();
        Iterable<DestinationEs> all = repository.findAll();
        all.forEach(a -> list.add(a));
        return list;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
