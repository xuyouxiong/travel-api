package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Visitor;
import cn.linstudy.travel.mapper.VisitorMapper;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.service.VisitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

  @Autowired
  VisitorService visitorService;

  @Autowired
  UserInfoService userInfoService;
  @Override
  public List<Visitor> queryVisitor(Long ownerId,Long userId) {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("owner_id", ownerId);
    List<Visitor> list = visitorService.list(queryWrapper);
    for (Visitor visitor : list) {
      visitor.setVisitor(userInfoService.getById(visitor.getVisitorId()));
    }
    return list;
  }
}
