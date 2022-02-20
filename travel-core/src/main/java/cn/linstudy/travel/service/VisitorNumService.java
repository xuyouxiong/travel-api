package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Visitor;
import cn.linstudy.travel.domain.VisitorNum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description
 * 
 */
public interface VisitorNumService extends IService<VisitorNum> {

  void addVistorNum(Long ownerId, Long userinfoId);

  VisitorNum queryVisitorNum(Long ownerId);
}
