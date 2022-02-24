package cn.linstudy.travel.search.service;


import cn.linstudy.travel.search.domain.StrategyEs;
import java.util.List;

public interface StrategyEsService {
    /** 添加
    * @param strategyEsEs
    * @return
     */
    void save(StrategyEs strategyEsEs);

    /**
     * 更新
     * @param strategyEsEs
     * @return
     */
    void update(StrategyEs strategyEsEs);

    /**
     * 查单个
     * @param id
     * @return
     */
    StrategyEs get(String id);

    /**
     * 查多个
     * @return
     */
    List<StrategyEs> list();

    /**
     * 删除
     * @param id
     */
    void delete(String id);

}
