package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.qo.StrategyCatalogQueryObject;
import cn.linstudy.travel.vo.StrategyCatalogVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description
 * 
 * @Date 2021/4/14 21:03
 */
public interface StrategyCatalogService extends IService<StrategyCatalog> {

  Page<StrategyCatalog> listForPage(StrategyCatalogQueryObject qo);

  List<StrategyCatalogVO> listCatalog();
}
