package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.qo.RegionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 地区的业务类
 * @Author XiaoLin
 * @Date 2021/4/13 8:50
 */

public interface RegionService  extends IService<Region> {

/**
    * @Description: 分页查询
    * @author XiaoLin
    * @date 2021/4/13
    * @Param: [qo]
    * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Region>
    */
  Page<Region> queryPage(RegionQueryObject qo);

  /**
      * @Description: 增加地区
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [region]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult insert(Region region);

  /**
      * @Description: 修改为热门或者普通
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [hot, id]
      * @return void
      */
  void changeHotValue(Boolean hot, Long id);

  /**
      * @Description: 删除地区
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: [id]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult delete(Long id);

  /**
      * @Description: 查找热门地区
      * @author XiaoLin
      * @date 2021/4/13
      * @Param: []
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult getHotRegion();


}
