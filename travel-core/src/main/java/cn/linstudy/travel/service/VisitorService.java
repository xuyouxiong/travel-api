package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Visitor;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description
 * 
 */
public interface VisitorService extends IService<Visitor> {

  List<Visitor> queryVisitor(Long ownerId,Long userId);
}
