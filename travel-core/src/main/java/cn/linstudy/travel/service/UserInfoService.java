package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mapper.UserInfoMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.vo.UserInfoLoginVO;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description 用户业务层接口
 * 
 * @Date 2021/4/9 14:21
 */
// 继承MyBatis-Plus的通用Service接口，泛型是实体类
public interface UserInfoService extends IService<UserInfo> {

  /**
      * @Description:检查手机是否注册过
      * 
      * @date 2021/4/9
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult checkPhone(String phone);

  /**
      * @Description:发送验证码
      * 
      * @date 2021/4/10
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult sendVerifyCode(String phone);

  /**
      * @Description:用户注册
      * 
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult register(UserInfoRegisterVO userInfoRegisterVO);

  /**
      * @Description: 登录
      * 
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult login(UserInfoLoginVO userInfoRegisterVO);

  /**
      * @Description: 校验根据id查询的用户是否存在
      * 
      * @date 2021/4/11
      * @Param: [parseLong]
      * @return cn.linstudy.travel.domain.UserInfo
      */
  UserInfo checkUserById(long parseLong);

  boolean favor(Long sid, Long userId);

  List<UserInfo> queryByDestName(String name);
}
