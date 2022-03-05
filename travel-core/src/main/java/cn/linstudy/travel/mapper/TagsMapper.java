package cn.linstudy.travel.mapper;

import cn.linstudy.travel.domain.Tags;
import cn.linstudy.travel.qo.response.TagsCountResponse;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagsMapper extends BaseMapper<Tags> {

    @Select("SELECT tag_id,count(*) as countTag from collection WHERE userinfo_id=${user_id} and tag_id != ${themeId} GROUP BY tag_id order by countTag desc limit 1")
    List<TagsCountResponse> selectCountTags(@Param("user_id") Long user_id, @Param("themeId") Long themeId);
}
