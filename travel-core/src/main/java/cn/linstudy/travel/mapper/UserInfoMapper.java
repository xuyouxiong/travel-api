package cn.linstudy.travel.mapper;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * @Description 用户Mapper
 * 
 * @Date 2021/4/9 14:20
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {// 继承MyBatis-Plus的通用Mapper，泛型是实体类

  @Insert("insert into userinfo( nickname, phone, password) values (#{nickname},#{phone},#{password})")
  void insert(UserInfoRegisterVO userInfoRegisterVO);

}
