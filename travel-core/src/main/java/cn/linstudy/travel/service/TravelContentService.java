package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.TravelContent;
import cn.linstudy.travel.qo.TravelConditionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 游记内容业务类接口
 * 
 * @Date 2021/4/17 16:03
 */
public interface TravelContentService extends IService<TravelContent> {

  /**
      * @Description: 通过id获取内容
      * 
      * @date 2021/4/17
      * @Param: [id]
      * @return cn.linstudy.travel.domain.TravelContent
      */
  TravelContent getContentById(Long id);

  /**
      * @Description: 审核内容
      * 
      * @date 2021/4/17
      * @Param: [id, state]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult audit(Long id, Integer state);

  JsonResult queryByDestId(TravelConditionQueryObject qo,Long destId);

  JsonResult detail(Long destId,Long id);
}
