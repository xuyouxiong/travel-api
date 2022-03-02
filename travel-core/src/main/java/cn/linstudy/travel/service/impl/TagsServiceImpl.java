package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.domain.Tags;
import cn.linstudy.travel.mapper.TagsMapper;
import cn.linstudy.travel.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {
}
