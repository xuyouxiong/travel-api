package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.mapper.RegionMapper;

import cn.linstudy.travel.qo.RegionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 地区的业务实现类
 * 
 * @Date 2021/4/13 9:00
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

  @Autowired
  DestinationService destinationService;

  @Override
  public Page<Region> queryPage(RegionQueryObject qo) {
    QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByAsc("seq");
    Page<Region> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
    return super.page(page,queryWrapper);
  }

  @Override
  public JsonResult insert(Region region) {
    super.save(region);
    return JsonResult.success();
  }

  /**
      * @Description: 修改为热门或者普通
      * 
      * @date 2021/4/13
      * @Param: [hot, id]
      * @return void
      */
  @Override
  public void changeHotValue(Boolean hot, Long id) {
    UpdateWrapper updateWrapper = new UpdateWrapper();
    updateWrapper.eq("id",id);
    updateWrapper.set("ishot",hot);
    super.update(updateWrapper);
  }

  /**
      * @Description: 删除
      * 
      * @date 2021/4/13
      * @Param: [id]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult delete(Long id) {
    super.removeById(id);
    return JsonResult.success();
  }

  /**
      * @Description: 获得热门地区
      * 
      * @date 2021/4/13
      * @Param: []
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult getHotRegion() {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("ishot",true);
    return JsonResult.success(super.list(queryWrapper));
  }


}

