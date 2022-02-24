package cn.linstudy.travel.controller;


import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.search.domain.DestinationEs;
import cn.linstudy.travel.search.domain.StrategyEs;
import cn.linstudy.travel.search.domain.TravelEs;
import cn.linstudy.travel.search.domain.UserInfoEs;
import cn.linstudy.travel.search.service.DestinationEsService;
import cn.linstudy.travel.search.service.StrategyEsService;
import cn.linstudy.travel.search.service.TravelEsService;
import cn.linstudy.travel.search.service.UserInfoEsService;
import cn.linstudy.travel.service.DestinationService;
import cn.linstudy.travel.service.StrategyService;
import cn.linstudy.travel.service.TravelService;
import cn.linstudy.travel.service.UserInfoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@RestController
public class DataController {

    //es服务

    @Autowired
    private DestinationEsService destinationEsService;
    @Autowired
    private StrategyEsService strategyEsService;

    @Autowired
    private TravelEsService travelEsService;

    @Autowired
    private UserInfoEsService userInfoEsService;


    //mysql服务
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private UserInfoService userInfoService;


    @PassLogin
    @ApiIgnore
    @GetMapping("/dataInit")
    public Object dataInit(){

        //攻略
        List<Strategy> sts = strategyService.list();
        for (Strategy st : sts) {
            StrategyEs es = new StrategyEs();
            BeanUtils.copyProperties(st, es);
            strategyEsService.save(es);
        }

        //游记
       List<Travel> ts = travelService.list();
        for (Travel t : ts) {
            TravelEs es = new TravelEs();
            BeanUtils.copyProperties(t, es);
            travelEsService.save(es);
        }




        //用户
        List<UserInfo> uf = userInfoService.list();
        for (UserInfo u : uf) {
            UserInfoEs es = new UserInfoEs();
            BeanUtils.copyProperties(u, es);
            userInfoEsService.save(es);
        }


        //目的地
        List<Destination> dests = destinationService.list();
        for (Destination d : dests) {
            DestinationEs es = new DestinationEs();
            BeanUtils.copyProperties(d, es);
            destinationEsService.save(es);
        }

        return "ok";
    }
}
