package cn.linstudy.travel.mapper;

import cn.linstudy.travel.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description 用户Mapper
 * @Author XiaoLin
 * @Date 2021/4/9 14:20
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> { // 继承MyBatis-Plus的通用Mapper，泛型是实体类
}
