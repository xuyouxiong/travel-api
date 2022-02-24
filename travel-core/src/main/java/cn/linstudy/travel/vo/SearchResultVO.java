package cn.linstudy.travel.vo;


import cn.linstudy.travel.domain.Destination;
import cn.linstudy.travel.domain.Strategy;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.UserInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchResultVO implements Serializable {


    private Long total = 0L;

    private List<Strategy> strategys = new ArrayList<>();
    private List<Travel> travels = new ArrayList<>();
    private List<UserInfo> users = new ArrayList<>();
    private List<Destination> dests = new ArrayList<>();

}
