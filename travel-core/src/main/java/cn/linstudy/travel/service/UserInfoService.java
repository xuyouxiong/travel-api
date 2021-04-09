package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mapper.UserInfoMapper;
import cn.linstudy.travel.qo.response.JsonResult;
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

  JsonResult sendVerifyCode(String phone);
}
