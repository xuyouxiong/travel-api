package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.Spot;
import cn.linstudy.travel.mapper.SpotMapper;
import cn.linstudy.travel.qo.SpotQueryObject;
import cn.linstudy.travel.service.SpotService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotServiceImpl extends ServiceImpl<SpotMapper, Spot> implements SpotService {

    @Override
    public Page<Spot> listForPage(SpotQueryObject qo, Long uid) {
        Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        List<Spot> spots = super.page(page,queryWrapper).getRecords();
        return page;
    }

    @Override
    public List<Spot> getSpots() {
        List<Spot> spots = super.list();
        return spots;
    }
}
