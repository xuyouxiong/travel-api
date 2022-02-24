package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Attraction;
import cn.linstudy.travel.qo.AttractionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 景点业务类
 * 
 * @Date 2021/4/14 18:36
 */
public interface AttractionService extends IService<Attraction> {

  JsonResult listForPage(AttractionQueryObject qo);

  JsonResult detail(Long id);
}
