package cn.linstudy.travel.interceptor;

import cn.linstudy.travel.annotation.PassLogin;
import cn.linstudy.travel.domain.Admin;
import cn.linstudy.travel.domain.UserInfo;
import cn.linstudy.travel.exception.LogicException;
import cn.linstudy.travel.qo.response.JsonResult;
import cn.linstudy.travel.service.AdminService;
import cn.linstudy.travel.service.UserInfoService;
import cn.linstudy.travel.utils.JwtUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        if( !(handler instanceof HandlerMethod)){
            return true;
        }
        //1：判断当前请求对应请求映射方法是否贴@RequireLogin标签
        HandlerMethod method = (HandlerMethod) handler;
        String token = request.getHeader("admin_token");

        if (method.hasMethodAnnotation(PassLogin.class)){
            return true;
        }else {
            if (token == null) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(JsonResult.noLogin()));
                return false;
            }else {
                //否则就是说明需要登录的
                //获得签发对象（用户id）
                String userId = JwtUtil.getAudience(token);
                System.out.println("从token中获取到的id" + userId);
                // 去数据库查询是否有这个用户，验证用户是否存在
                Admin admin = adminService.getById(Long.parseLong(userId));

                // 如果为空，说明用户不存在
                if (admin == null) {
                    throw new LogicException("用户不存在");
                }
                // 验证Token是否合法
//        JwtUtil.verify(token, userId);
                return true;
            }
        }

    }
}
