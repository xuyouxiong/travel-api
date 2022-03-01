package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.Spot;
import cn.linstudy.travel.qo.OrderQueryObject;
import cn.linstudy.travel.qo.SpotQueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpotService extends IService<Spot> {
    Page<Spot> listForPage(SpotQueryObject qo, Long uid);

    List<Spot> getSpots();
}
