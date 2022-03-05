package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Tags;
import cn.linstudy.travel.mapper.TagsMapper;
import cn.linstudy.travel.qo.response.TagsCountResponse;
import cn.linstudy.travel.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {
    @Autowired
    TagsMapper tagsMapper;


    @Override
    public List<TagsCountResponse> selectCountTags(Long uid, Long themeId) {
        return tagsMapper.selectCountTags(uid, themeId);
    }
}
