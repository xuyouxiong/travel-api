package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Attraction;
import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.mapper.AttractionMapper;
import cn.linstudy.travel.qo.AttractionQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.AttractionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description 景点业务类
 * 
 * @Date 2021/4/14 18:39
 */
@Service
public class AttractionServiceImpl extends ServiceImpl<AttractionMapper, Attraction> implements AttractionService {

  @Override
  public JsonResult listForPage(AttractionQueryObject qo) {
   Page<Attraction> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
    Page<Attraction> attractionPage = super.page(page);
    return JsonResult.success(attractionPage);
  }

  @Override
  public JsonResult detail(Long id) {
    Attraction attraction = super.getById(id);
    return JsonResult.success(attraction);
  }
}
