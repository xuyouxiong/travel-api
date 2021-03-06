package cn.linstudy.travel.service.impl;

import cn.linstudy.travel.constant.SystemConstant;
import cn.linstudy.travel.domain.Admin;
import cn.linstudy.travel.domain.Region;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.mapper.AdminInfoMapper;
import cn.linstudy.travel.qo.AdminQueryObject;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.redis.RedisKeyEnum;
import cn.linstudy.travel.redis.service.UserInfoRedisService;
import cn.linstudy.travel.redis.service.impl.UserInfoRedisServiceImpl;
import cn.linstudy.travel.service.AdminService;
import cn.linstudy.travel.utils.JwtUtil;
import cn.linstudy.travel.vo.AdminInfoVo;
import cn.linstudy.travel.vo.AdminRegisterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public JsonResult register(AdminRegisterVo registerVo) {
        Admin admin = new Admin();
        admin.setStatus(0);
        admin.setRole(2);
        admin.setName(registerVo.getName());
        admin.setCert(registerVo.getCert());
        admin.setFaren(registerVo.getFaren());
        admin.setPass(registerVo.getPass());
        admin.setPhone(registerVo.getPhone());
        adminInfoMapper.insert(admin);
        return null;
    }

    @Override
    public Page<Admin> queryList(AdminQueryObject qo) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        Page<Admin> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
        return super.page(page,queryWrapper);
    }

    @Override
    public JsonResult login(AdminInfoVo adminInfoVo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",adminInfoVo.getName());
        Admin admin = adminInfoMapper.selectOne(wrapper);
        if (admin != null){
            if (admin.getPass().equals(adminInfoVo.getPass())){
                if(admin.getStatus().equals(1)) {
                    Map<String, String> map = new HashMap<>();//????????????payload
                    map.put("name",admin.getName());
                    String token = JwtUtil.getToken(Long.toString(admin.getId()),map);
                    String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(admin.getName());

                    System.out.println("token = " + token + ", key = " + key);
                    String LoginToken = userInfoRedisService.getValue(key);
                    if (LoginToken != null){
                        userInfoRedisService.resetTime(key);
                    }
                    // ???token??????redis???
//       String key = RedisKeyEnum.ENUM_LOGIN_TOKEN.join(userInfoLoginVO.getPhone());
                    userInfoRedisService.setLoginToekn(key,token);
                    Map<String,Object> resultMap = new HashMap<>();
                    resultMap.put("token",token);
                    resultMap.put("user",admin);
                    return  JsonResult.success(resultMap);
                } else {
                    return  JsonResult.error(402, "???????????????????????????");
                }
            }else {
                return  JsonResult.error(402, "????????????/????????????");
            }
        }

        return  JsonResult.error(402, "????????????/????????????");

//        throw new LogicException("??????????????????");
    }
}
