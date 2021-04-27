package cn.linstudy.travel.controller;

import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.search.domain.DestinationEs;
import cn.linstudy.travel.search.domain.StrategyEs;
import cn.linstudy.travel.search.domain.TravelEs;
import cn.linstudy.travel.search.domain.UserInfoEs;
import cn.linstudy.travel.search.qo.SearchQueryObject;
import cn.linstudy.travel.search.service.SearchService;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.utils.ParamMap;
import cn.linstudy.travel.vo.SearchResultVO;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author XiaoLin
 * @Date 2021/4/22 21:07
 */
@RestController
public class SearchController {

  @Autowired
  DestinationService destinationService;

  @Autowired
  StrategyService strategyService;

  @Autowired
  TravelService travelService;

  @Autowired
  UserInfoService userInfoService;

  @Autowired
  SearchService searchService;

  @GetMapping("/q")
  public JsonResult search(SearchQueryObject qo) throws UnsupportedEncodingException {
    //URL解码
    String kw = URLDecoder.decode(qo.getKeyword(), "UTF-8");
    qo.setKeyword(kw);
    switch (qo.getType()){
      case SearchQueryObject.TYPE_DEST:return searchDest(qo);
      case SearchQueryObject.TYPE_STRATEGY:return searchStrategy(qo);
      case SearchQueryObject.TYPE_TRAVEL:return searchTravel(qo);
      case SearchQueryObject.TYPE_USER:return searchUserInfo(qo);
      default:return searchAll(qo);
    }

  }

  private JsonResult searchUserInfo(SearchQueryObject qo) {
    return JsonResult.success(
        ParamMap.newInstance().put("page", this.createUserInfoPage(qo)).put("qo", qo));
  }

  private Page<UserInfo> createUserInfoPage(SearchQueryObject qo) {
    return searchService.searchWithHighlight(UserInfoEs.INDEX_NAME,UserInfo.class,qo,"info", "city");
  }

  private JsonResult searchTravel(SearchQueryObject qo) {
    return JsonResult.success(
        ParamMap.newInstance().put("page", this.createTravelPage(qo)).put("qo", qo));
  }

  private Page<Travel> createTravelPage(SearchQueryObject qo){
    Page<Travel> ts =searchService.searchWithHighlight(TravelEs.INDEX_NAME, Travel.class,
        qo,"title", "summary"
    );
    for (Travel t : ts) {
      t.setAuthor(userInfoService.getById(t.getAuthorId()));
    }

    return ts;
  }

  private JsonResult searchStrategy(SearchQueryObject qo) {
    return JsonResult.success(
        ParamMap.newInstance().put("page", this.createStrategyPage(qo)).put("qo", qo));
  }

  private Page<Strategy> createStrategyPage(SearchQueryObject qo) {
    return searchService.searchWithHighlight(StrategyEs.INDEX_NAME,Strategy.class,qo,"title", "subTitle", "summary");
  }

  private JsonResult searchAll(SearchQueryObject qo) {
    SearchResultVO vo = new SearchResultVO();
    List<Destination> destinationList = this.createDestinationPage(qo).getContent();
    List<UserInfo> userInfoList =this.createUserInfoPage(qo).getContent();
    List<Travel> travelList = this.createTravelPage(qo).getContent();
    List<Strategy> strategyList = this.createStrategyPage(qo).getContent();
    vo.setDests(destinationList);
    vo.setUsers(userInfoList);
    vo.setTravels(travelList);
    vo.setStrategys(strategyList);
    vo.setTotal(destinationList.size()+userInfoList.size()+travelList.size()+strategyList.size()+0L);
    Map map = new HashMap();
    map.put("result",vo);
    map.put("qo",qo);
    return JsonResult.success(map);
  }

  private Page<Destination> createDestinationPage(SearchQueryObject qo){
    return  searchService.searchWithHighlight(DestinationEs.INDEX_NAME, Destination.class,
        qo,"name", "info"
    );
  }

  private JsonResult searchDest(SearchQueryObject qo) {
    Map<String, Object> map = new HashMap<>();
    SearchResultVO vo = new SearchResultVO();
    Destination destination = destinationService.queryByName(qo.getKeyword());
    if (destination!=null){
      List<Strategy> strategyList = strategyService.queryByDestId(destination.getId());
      List<Travel> travelList = travelService.queryByDestId(destination.getId());
      List<UserInfo> userInfoList = userInfoService.queryByDestName(destination.getName());
      vo.setStrategys(strategyList);
      vo.setTravels(travelList);
      vo.setUsers(userInfoList);
      vo.setTotal(strategyList.size()+travelList.size()+userInfoList.size()+0L);
      map.put("result",vo);
      map.put("dest",destination);
      map.put("qo",qo);
    }

    return JsonResult.success(map);
  }


}
