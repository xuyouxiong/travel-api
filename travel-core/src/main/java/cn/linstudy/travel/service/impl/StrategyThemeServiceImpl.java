package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.StrategyTheme;
import cn.linstudy.travel.mapper.StrategyThemeMapper;
import cn.linstudy.travel.qo.StrategyThemeQueryObject;
import cn.linstudy.travel.service.StrategyThemeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/14 20:26
 */
@Service
public class StrategyThemeServiceImpl extends
    ServiceImpl<StrategyThemeMapper, StrategyTheme> implements
    StrategyThemeService {

  @Override
  public Page<StrategyTheme> listForPage(StrategyThemeQueryObject qo) {
    Page<StrategyTheme> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
    return super.page(page);
  }
}
