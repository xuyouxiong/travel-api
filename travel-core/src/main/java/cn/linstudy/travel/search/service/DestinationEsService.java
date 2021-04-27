package cn.linstudy.travel.search.service;

import cn.linstudy.travel.search.domain.DestinationEs;
import java.util.List;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/22 21:11
 */
public interface DestinationEsService {

  /** 添加
   * @param destinationEsEs
   * @return
   */
  void save(DestinationEs destinationEsEs);

  /**
   * 更新
   * @param destinationEsEs
   * @return
   */
  void update(DestinationEs destinationEsEs);

  /**
   * 查单个
   * @param id
   * @return
   */
  DestinationEs get(String id);

  /**
   * 查多个
   * @return
   */
  List<DestinationEs> list();

  /**
   * 删除
   * @param id
   */
  void delete(String id);
}
