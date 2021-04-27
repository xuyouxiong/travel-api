package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.mapper.TravelMapper;
import cn.linstudy.travel.mongo.domain.TravelComment;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 游记相关的业务实现类
 * @Author XiaoLin
 * @Date 2021/4/17 14:11
 */
@Service
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements TravelService {

  @Autowired
  UserInfoService userInfoService;
  /**
      * @Description: 分页查询所有游记
      * @author XiaoLin
      * @date 2021/4/17
      * @Param: [qo]
      * @return java.util.List<cn.linstudy.travel.domain.Travel>
      */
  @Override
  public Page<Travel> listForPage(TravelQueryObject qo) {
    Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq(qo.getState() != null && qo.getState() != -1,"state",qo.getState());
    List<Travel> travels = super.page(page,queryWrapper).getRecords();
    for (Travel travel : travels) {
     travel.setAuthor(userInfoService.getById(travel.getAuthorId()));
    }
    return page;
  }

  @Override
  public JsonResult viewnumTop3(Long destId) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.orderByDesc("viewnum");
    queryWrapper.last("limit 3");
    return JsonResult.success(super.list(queryWrapper));
  }

  @Override
  public List<Travel> queryByDestId(Long id) {
    List<Travel> travelList = super.list(new QueryWrapper<Travel>().eq("dest_id", id));
    for (Travel travel : travelList) {
      travel.setAuthor(userInfoService.getById(travel.getAuthorId()));
    }
    return travelList;
  }


}
