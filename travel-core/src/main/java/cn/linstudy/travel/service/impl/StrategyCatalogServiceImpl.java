package cn.linstudy.travel.service.impl;


import cn.linstudy.travel.domain.StrategyCatalog;
import cn.linstudy.travel.mapper.StrategyCatalogMapper;
import cn.linstudy.travel.qo.StrategyCatalogQueryObject;
import cn.linstudy.travel.service.StrategyCatalogService;
import cn.linstudy.travel.vo.StrategyCatalogVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @Description
 * 
 * @Date 2021/4/14 21:04
 */
@Service
public class StrategyCatalogServiceImpl extends
    ServiceImpl<StrategyCatalogMapper, StrategyCatalog> implements
    StrategyCatalogService {

  @Override
  public Page<StrategyCatalog> listForPage(StrategyCatalogQueryObject qo) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.orderByAsc("seq");
    Page<StrategyCatalog> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
    return super.page(page,queryWrapper);
  }

  // todo 查询所有分类
  /**
      * @Description: 查询所有分类
      * 
      * @date 2021/4/15
      * @Param: []
      * @return java.util.List<cn.linstudy.travel.vo.StrategyCatalogVO>
      */
  @Override
  public List<StrategyCatalogVO> listCatalog() {
    List<StrategyCatalogVO> list = new ArrayList<>();
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.select("dest_name,GROUP_CONCAT(id) ids,GROUP_CONCAT(name) names");
    queryWrapper.groupBy("dest_name");
    List<Map<String,Object>> mapList = super.listMaps(queryWrapper);
    for (Map<String, Object> map : mapList) {
      StrategyCatalogVO strategyCatalogVO = new StrategyCatalogVO();
      String dest_name = map.get("dest_name").toString();
      strategyCatalogVO.setDestName(dest_name);
      String ids = map.get("ids").toString();
      String names = map.get("names").toString();
      strategyCatalogVO.setCatalog(parseIdsAndNameToStrategyCatalog(ids,names));
      list.add(strategyCatalogVO);
    }
    return list;
  }

  /**
      * @Description: 解析字符串类型的多个名字和多个id，封装成一个List<StrategyCatalog>
      * 
      * @date 2021/4/15
      * @Param: [ids, names]
      * @return java.util.List<cn.linstudy.travel.domain.StrategyCatalog>
      */
  private List<StrategyCatalog> parseIdsAndNameToStrategyCatalog(String ids, String names) {
    List<StrategyCatalog> list = new ArrayList<>();
    String[] idAttr = ids.split(",");
    String[] nameAttr = names.split(",");
    for (int i = 0; i < idAttr.length; i++) {
      StrategyCatalog strategyCatalog = new StrategyCatalog();
      String id = idAttr[i];
      String name = nameAttr[i];
      strategyCatalog.setId(Long.valueOf(id));
      strategyCatalog.setName(name);
      list.add(strategyCatalog);
    }
    return list;
  }
}
