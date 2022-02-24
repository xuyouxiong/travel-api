package cn.linstudy.travel.mapper;

import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Description 游记相关的mapper接口
 *
 * @Date 2021/4/17 14:09
 */
public interface TravelMapper extends BaseMapper<Travel> {

}
