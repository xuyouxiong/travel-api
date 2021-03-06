package cn.linstudy.travel.service.impl;


import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.Travel;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.mapper.UserInfoMapper;
import cn.linstudy.travel.qo.UserInfoQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.utils.AssertsUtils;
import cn.linstudy.travel.utils.JwtUtil;
import cn.linstudy.travel.utils.SendMessageUtils;
import cn.linstudy.travel.utils.VerifyCodeUtils;
import cn.linstudy.travel.vo.UserInfoLoginVO;
import cn.linstudy.travel.vo.UserInfoRegisterVO;
import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.exceptions.ClientException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 用户业务层接口实现类
 * 
 * @Date 2021/4/9 14:23
 */
@Service
@Transactional
// 实现MyBatis-Plus的通用Service实现类，泛型参数一是mapper接口，第二个是用户实体类
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements UserInfoService {

  @Autowired
  UserInfoMapper userInfoMapper;

  @Autowired
  UserInfoRedisService userInfoRedisService;

  @Autowired
  StringRedisTemplate redisTemplate;
  /**
      * @Description: 检查手机是否注册过实现类
      * 
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

  /**
      * @Description: 发送验证码实现类
      * 
      * @date 2021/4/10
      * @Param: [phone]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult sendVerifyCode(String phone) {
    try {
      String code = VerifyCodeUtils.generateVerifyCode(4);
      SendMessageUtils.sendSms(phone,code);
      userInfoRedisService.setVerifyCode(phone,code);
      return JsonResult.success();
    } catch (Exception e) {
      return JsonResult.error(SystemConstant.CODE_SEND_PHONE_MESSAGE,e.getMessage());
    }
  }

  /**
      * @Description: 用户注册实现类
      * 
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult register(UserInfoRegisterVO userInfoRegisterVO) {
    AssertsUtils.hasText(userInfoRegisterVO.getNickname(),"昵称不能为空");
    AssertsUtils.hasText(userInfoRegisterVO.getPassword(),"密码不能为空");
    AssertsUtils.hasText(userInfoRegisterVO.getRepeatPassword(),"再次密码不能为空");
    AssertsUtils.hasText(userInfoRegisterVO.getPhone(),"手机不能为空");
    AssertsUtils.isEquals(userInfoRegisterVO.getPassword(),userInfoRegisterVO.getRepeatPassword(),"两次的密码不一样");
    AssertsUtils.isEquals(userInfoRegisterVO.getVerifyCode(), userInfoRedisService.getValue(RedisKeyEnum.ENUM_VERYFY_CODE.join(userInfoRegisterVO.getPhone())), "手机验证码错误");
    System.out.println(userInfoRegisterVO.getVerifyCode());
    System.out.println(userInfoRedisService.getValue(RedisKeyEnum.ENUM_VERYFY_CODE.join(userInfoRegisterVO.getPhone())));
    try {
      // 注册
    userInfoMapper.insert(userInfoRegisterVO);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new JsonResult(SystemConstant.CODE_SUCCESS,SystemConstant.MSG_SUCCESS);
  }

  /**
      * @Description: 登录实现类
      * 
      * @date 2021/4/10
      * @Param: [userInfoRegisterVO]
      * @return cn.linstudy.travel.qo.response.JsonResult
      */
  @Override
  public JsonResult login(UserInfoLoginVO userInfoLoginVO) {
    QueryWrapper wrapper = new QueryWrapper();
    wrapper.eq("phone",userInfoLoginVO.getPhone());
    UserInfo userInfo = userInfoMapper.selectOne(wrapper);
    if (userInfo != null){
     if (userInfo.getPassword().equals(userInfoLoginVO.getPassword())){
       Map<String, String> map = new HashMap<>();//用来存放payload
       map.put("phone",userInfo.getPhone());
       String token = JwtUtil.getToken(Long.toString(userInfo.getId()),map);
       String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
       String LoginToken = userInfoRedisService.getValue(key);
       if (LoginToken != null){
         userInfoRedisService.resetTime(key);
       }
       // 将token放入redis中
//       String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
       userInfoRedisService.setLoginToekn(key,token);
       Map<String,Object> resultMap = new HashMap<>();
       resultMap.put("token",token);
       resultMap.put("user",userInfo);
       return  JsonResult.success(resultMap);
     }else {
       throw new LogicException("密码错误");
     }
    }
    throw new LogicException("手机号未注册");
  }

  /**
      * @Description: 校验根据id查询的用户是否存在
      * 
      * @date 2021/4/11
      * @Param: [parseLong]
      * @return cn.linstudy.travel.domain.UserInfo
      */
  @Override
  public UserInfo checkUserById(long id) {
    return userInfoMapper.selectById(id);
  }

  /**
      * @Description: 判断是白色星星还是黑色星星
      * 
      * @date 2021/4/20
      * @Param: [sid, userId]
      * @return boolean
      */
  @Override
  public boolean favor(Long sid, Long userId) {
    String key = RedisKeyEnum.STRATEGY_FAVOR.join(userId.toString());
    boolean flag = false;
    List<Long> favorNumList = null;
    if (redisTemplate.hasKey(key)){
     String favorList =  redisTemplate.opsForValue().get(key);
      favorNumList = JSONArray.parseArray(favorList,Long.class);
      if (favorNumList.contains(sid)) {
          // 说明有收藏，变为黑色星星
          flag = true;
        }
      }
    return flag;
    }

  @Override
  public List<UserInfo> queryByDestName(String name) {
    return super.list(new QueryWrapper<UserInfo>().eq("city",name));
  }

  @Override
  public Page<UserInfo> listForPage(UserInfoQueryObject qo) {
    Page page = new Page(qo.getCurrentPage(),qo.getPageSize());
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("state", qo.getState());
    List<UserInfo> userInfos = super.page(page,queryWrapper).getRecords();
    return super.page(page);
  }
}

