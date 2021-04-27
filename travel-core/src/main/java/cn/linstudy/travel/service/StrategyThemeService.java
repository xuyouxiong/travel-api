package cn.linstudy.travel.service;


import cn.linstudy.travel.domain.StrategyTheme;
import cn.linstudy.travel.qo.StrategyQueryObject;
import cn.linstudy.travel.qo.StrategyThemeQueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/14 20:22
 */
public interface StrategyThemeService extends IService<StrategyTheme> {

  Page<StrategyTheme> listForPage(StrategyThemeQueryObject qo);
}
