package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.Admin;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.mapper.AdminInfoMapper;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import cn.linstudy.travel.redis.service.impl.UserInfoRedisServiceImpl;
import cn.linstudy.travel.service.AdminService;
import cn.linstudy.travel.utils.JwtUtil;
import cn.linstudy.travel.vo.AdminInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminInfoMapper, Admin> implements AdminService {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Autowired
    UserInfoRedisService userInfoRedisService;

    @Override
    public boolean checkName(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",name);
        Admin admin = adminInfoMapper.selectOne(wrapper);
        if (admin == null){
            return true;
        }

        return false;
    }

    @Override
    public JsonResult login(AdminInfoVo adminInfoVo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",adminInfoVo.getName());
        Admin admin = adminInfoMapper.selectOne(wrapper);
        if (admin != null){
            if (admin.getPass().equals(adminInfoVo.getPass())){
                Map<String, String> map = new HashMap<>();//用来存放payload
                map.put("name",admin.getName());
                String token = JwtUtil.getToken(Long.toString(admin.getId()),map);
                String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(admin.getName());

                System.out.println("token = " + token + ", key = " + key);
                String LoginToken = userInfoRedisService.getValue(key);
                if (LoginToken != null){
                    userInfoRedisService.resetTime(key);
                }
                // 将token放入redis中
//       String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
                userInfoRedisService.setLoginToekn(key,token);
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("token",token);
                resultMap.put("user",admin);
                return  JsonResult.success(resultMap);
            }else {
                return  JsonResult.error(402, "用户账户/密码错误");
            }
        }

        return  JsonResult.error(402, "用户账户/密码错误");

//        throw new LogicException("该用户未注册");
    }
}
