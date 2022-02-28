package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.mapper.HotelMapper;
import cn.linstudy.travel.qo.HotelFrontQueryObject;
import cn.linstudy.travel.qo.HotelQueryObject;
import cn.linstudy.travel.service.AdminService;
import cn.linstudy.travel.service.HotelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
    @Autowired
    AdminService adminService;

    @Override
    public Page<Hotel> listForPage(HotelQueryObject qo, Long uid) {
        Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 1);
        if (uid != 1) {
            queryWrapper.eq("uid", uid);
        }
        List<Hotel> hotels = super.page(page,queryWrapper).getRecords();
        for (Hotel hotel : hotels) {
            hotel.setUsername(adminService.getById(hotel.getUid()).getName());
        }
        return page;
    }

    @Override
    public List<Hotel> queryByDestId(HotelFrontQueryObject qo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (qo.getName() != null) {
            queryWrapper.eq("name", qo.getName());
        }

        if (qo.getStartDate() != null) {
            queryWrapper.ge("end_date", qo.getEndDate());
        }

        if (qo.getStartDate() != null) {
            queryWrapper.le("start_date", qo.getStartDate());
        }

        if (qo.getName() != null) {
            queryWrapper.eq("name", qo.getName());
        }

        queryWrapper.eq("status", 1);
        List<Hotel> hotels = super.list(queryWrapper);
        return hotels;
    }
}
