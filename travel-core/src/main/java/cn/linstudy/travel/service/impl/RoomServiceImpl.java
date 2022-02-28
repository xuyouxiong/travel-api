package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Room;
import cn.linstudy.travel.mapper.HotelMapper;
import cn.linstudy.travel.mapper.RoomMapper;
import cn.linstudy.travel.qo.RoomFrontQueryObject;
import cn.linstudy.travel.qo.RoomQueryObject;
import cn.linstudy.travel.service.HotelService;
import cn.linstudy.travel.service.RoomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    HotelService hotelService;


    @Override
    public Page<Room> listForPage(RoomQueryObject qo, Long id) {
        Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("hotel_id", id);
        List<Room> rooms = super.page(page,queryWrapper).getRecords();
        for (Room room : rooms) {
            room.setHotelName(hotelService.getById(room.getHotelId()).getName());
        }
        return page;
    }

    @Override
    public List<Room> queryByDestId(RoomFrontQueryObject qo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("hotel_id", qo.getHotelId());
        List<Room> rooms = super.list(queryWrapper);
        return rooms;

    }
}
