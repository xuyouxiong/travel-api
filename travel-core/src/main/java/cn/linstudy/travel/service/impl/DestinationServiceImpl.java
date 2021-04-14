package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.mapper.DestinationMapper;
import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.RegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/13 14:40
 */
@Service
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements DestinationService {

  @Autowired
  DestinationMapper destinationMapper;

  @Autowired
  RegionService regionService;

  /**
      * @Description: 查询所有
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [qo]
      * @return java.util.List<cn.linstudy.travel.domain.Destination>
      */
  @Override
  public List<Destination> listAll(DestinationQueryObject qo) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq(qo.getKeyword()!=null,"name",qo.getKeyword());
    return super.list();
  }

  /**
      * @Description: 增加
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [destination]
      * @return void
      */
  @Override
  public void insert(Destination destination) {
    super.save(destination);
  }

  /**
      * @Description: 分页加高级查询
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [qo]
      * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Destination>
      */
  @Override
  public Page<Destination> queryList(DestinationQueryObject qo) {
    QueryWrapper<Destination> queryWrapper = new QueryWrapper<>();
    Page<Destination> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
    queryWrapper.like(qo.getKeyword()!=null,"name",qo.getKeyword());
    queryWrapper.eq(qo.getParentId()!=null,"parent_id",qo.getParentId());
    return super.page(page,queryWrapper);
  }

  /**
      * @Description: 删除目的地
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [id]
      * @return void
      */
  @Override
  public void deleteById(Long id) {
    super.removeById(id);
  }

  /**
      * @Description: 查看前端界面的前几级目录
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [qo]
      * @return java.util.Map
      */
  @Override
  public List<Destination> getToasts(Long parentId) {
    // 创建一个集合
    List<Destination>  list = new ArrayList<>();
    // 调用方法去找所有上级
      createToast(list,parentId);
      Collections.reverse(list);
    return list;
  }

  @Override
  public List<Destination> searchDestination(Long regionId) {
    List<Destination> list = new ArrayList<>();
    QueryWrapper<Destination> wrapper = new QueryWrapper<>();
    //第一层：挂载目的地集合
    //国内
    if(regionId == -1){
      //查询中国所有省份
      wrapper.eq("parent_name", "中国");
      list = super.list(wrapper);
    }else {
      //非国内
      Region region = regionService.getById(regionId);
      list = super.listByIds(region.parseRefIds());
    }
    //第二层：目的地集合中子目的地
    for (Destination dest : list) {
      wrapper.clear();   //清空所有条件设置
      wrapper.eq("parent_id", dest.getId())
          .last("limit 5");
      List<Destination> children = super.list(wrapper);

      dest.setChildren(children);
    }
    return list;
  }


  private void createToast(List<Destination> list,Long parentId) {
    if (parentId == null){
      return;
    }
    // 这个找的是他的上一级，把parent_id作为id去查找可以找到上一级
    Destination destination = super.getById(parentId);
    // 添加到集合中
    list.add(destination);
    // 如果1parent_id不为空，说明不是顶级的，可以继续找
    if (destination.getParentId()!= null){
      createToast(list,destination.getParentId());
    }
  }



}
