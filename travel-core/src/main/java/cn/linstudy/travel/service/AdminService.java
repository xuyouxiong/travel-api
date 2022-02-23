package cn.linstudy.travel.service;

import cn.linstudy.travel.domain.Admin;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.vo.AdminInfoVo;
import cn.linstudy.travel.vo.AdminRegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    /**
     * 检查这个用户是否注册过
     * @param name
     * @return
     */
    boolean checkName(String name);

    /**
     * 用户登录
     * @param adminInfoVo
     * @return
     */
    JsonResult login(AdminInfoVo adminInfoVo);

    /**
     *
     * @param registerVo
     * @return
     */
    JsonResult register(AdminRegisterVo registerVo);
}
