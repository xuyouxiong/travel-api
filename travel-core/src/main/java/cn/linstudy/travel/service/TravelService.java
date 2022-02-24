package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.mongo.domain.TravelComment;
import cn.linstudy.travel.qo.TravelQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description 分页查询所有
 * 
 * @Date 2021/4/17 14:11
 */
public interface TravelService extends IService<Travel> {

  /**
      * @Description: 分页查询
      * 
      * @date 2021/4/17
      * @Param: [qo]
      * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.linstudy.travel.domain.Travel>
      */
  Page<Travel> listForPage(TravelQueryObject qo);

  /**
      * @Description: 查询前三的游记
      * 
      * @date 2021/4/17
      * @Param: [destId]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult viewnumTop3(Long destId);


  List<Travel> queryByDestId(Long id);
}
