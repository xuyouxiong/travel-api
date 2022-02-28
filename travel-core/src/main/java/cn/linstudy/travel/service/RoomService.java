package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Hotel;
import cn.linstudy.travel.domain.Room;
import cn.linstudy.travel.qo.HotelFrontQueryObject;
import cn.linstudy.travel.qo.HotelQueryObject;
import cn.linstudy.travel.qo.RoomFrontQueryObject;
import cn.linstudy.travel.qo.RoomQueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoomService extends IService<Room> {
    Page<Room> listForPage(RoomQueryObject qo, Long id);
    List<Room> queryByDestId(RoomFrontQueryObject qo);
}
