package cn.linstudy.travel.service.impl;


import cn.linstudy.travel.domain.Orders;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.mapper.OrdersMapper;
import cn.linstudy.travel.qo.OrderQueryObject;
import cn.linstudy.travel.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrderService {
    @Override
    public Page<Orders> listForPage(OrderQueryObject qo) {
        Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Orders> orders = super.page(page,queryWrapper).getRecords();
        return page;
    }

    @Override
    public List<Orders> getOrders(Long uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        return super.list(queryWrapper);
    }
}
