package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Tags;
import cn.linstudy.travel.qo.response.TagsCountResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagsService extends IService<Tags> {

    List<TagsCountResponse> selectCountTags(Long uid, Long themeId);
}
