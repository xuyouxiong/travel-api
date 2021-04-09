package cn.linstudy.travel.service.impl;


import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.mapper.UserInfoMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.utils.SendMessageUtils;
import cn.linstudy.travel.utils.VerifyCodeUtils;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 用户业务层接口实现类
 * @Author XiaoLin
 * @Date 2021/4/9 14:23
 */
@Service
@Transactional
// 实现MyBatis-Plus的通用Service实现类，泛型参数一是mapper接口，第二个是用户实体类
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements UserInfoService {

  @Autowired
  UserInfoMapper userInfoMapper;
  /**
      * @Description: 检查手机是否注册过
      * @author XiaoLin
      * @date 2021/4/9
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult checkPhone(String phone) {
    QueryWrapper wrapper = new QueryWrapper();
    wrapper.eq("phone",phone);
    UserInfo userInfo = userInfoMapper.selectOne(wrapper);
    if (userInfo == null){
      return JsonResult.success();
    }
    return JsonResult.error(SystemConstant.CODE_PHONE_REGISTER,SystemConstant.PHONE_REGISTER);
  }

  @Override
  public JsonResult sendVerifyCode(String phone) {
    try {
      SendMessageUtils.sendShortMessage("SMS_213078152",phone,VerifyCodeUtils.generateVerifyCode(4));
      return JsonResult.success();
    } catch (ClientException e) {
      return JsonResult.error(SystemConstant.CODE_SEND_PHONE_MESSAGE,e.getMessage());

    }
  }

}
