package org.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：林梓龙
 * <p>拦截器类</p>
 * @description：TODO
 * @date ：2022/11/20 0020 22:31
 */
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {




        if (null == request.getSession().getAttribute("userInfo")) {
            //对应拦截路径,没有登录,跳转到登录页面!
            //request.getSession().setAttribute(Constants.USER_ERROR_MSG, "请重新登陆");
            //重定向到登录页面
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        } else {
            //放行
            return true;
        }
    }
}