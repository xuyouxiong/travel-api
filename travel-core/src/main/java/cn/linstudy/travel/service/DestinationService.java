package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.qo.DestinationQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.awt.Desktop;
import java.util.List;
import java.util.Map;

/**
 * @Description 目的地的业务类接口
 * 
 * @Date 2021/4/13 14:39
 */
public interface DestinationService extends IService<Destination> {

  /**
      * @Description: 查询所有目的地
      * 
      * @date 2021/4/13
      * @Param: []
      * @return java.util.List<cn.linstudy.travel.domain.Destination>
      */
  List<Destination> listAll(DestinationQueryObject qo);

  /**
      * @Description:插入目的地
      * 
      * @date 2021/4/13
      * @Param: [destination]
      * @return void
      */
  void insert(Destination destination);

  /**
      * @Description: 分页查询所有目的地
      * 
      * @date 2021/4/13
      * @Param: [qo]
      * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Destination>
      */
  Page<Destination> queryList(DestinationQueryObject qo);

  /**
      * @Description: 通过id删除目的地
      * 
      * @date 2021/4/13
      * @Param: [id]
      * @return void
      */
  void deleteById(Long id);

  /**
      * @Description: 获取前端界面的上几级目录
      * 
      * @date 2021/4/13
      * @Param: [qo]
      * @return java.lang.Object
      */
  List<Destination> getToasts(Long parentId);


  /**
      * @Description: 根据区域查询该区域底下的目的地
      * 
      * @date 2021/4/14
      * @Param: [regionId]
      * @return java.util.List<cn.linstudy.travel.domain.Destination>
      */
  List<Destination> searchDestination(Long regionId);

  /**
      * @Description: 通过名字查询目的地
      * 
      * @date 2021/4/22
      * @Param: [keyword]
      * @return java.awt.Desktop
      */
  Destination queryByName(String name);
}
