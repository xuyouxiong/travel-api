package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mapper.UserInfoMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.vo.UserInfoLoginVO;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 用户业务层接口
 * @Author XiaoLin
 * @Date 2021/4/9 14:21
 */
// 继承MyBatis-Plus的通用Service接口，泛型是实体类
public interface UserInfoService extends IService<UserInfo> {

  /**
      * @Description:检查手机是否注册过
      * @author XiaoLin
      * @date 2021/4/9
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult checkPhone(String phone);

  /**
      * @Description:发送验证码
      * @author XiaoLin
      * @date 2021/4/10
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult sendVerifyCode(String phone);

  /**
      * @Description:用户注册
      * @author XiaoLin
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult register(UserInfoRegisterVO userInfoRegisterVO);

  /**
      * @Description: 登录
      * @author XiaoLin
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  JsonResult login(UserInfoLoginVO userInfoRegisterVO);
}