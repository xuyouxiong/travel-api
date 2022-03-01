package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.qo.OrderQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Orders> {
    Page<Orders> listForPage(OrderQueryObject qo);

    List<Orders> getOrders(Long uid);
}
