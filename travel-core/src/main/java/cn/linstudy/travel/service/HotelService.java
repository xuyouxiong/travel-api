package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.qo.HotelFrontQueryObject;
import cn.linstudy.travel.qo.HotelQueryObject;
import cn.linstudy.travel.qo.TravelQueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HotelService extends IService<Hotel> {
    Page<Hotel> listForPage(HotelQueryObject qo, Long uid);

    List<Hotel> queryByDestId(HotelFrontQueryObject qo);
}
