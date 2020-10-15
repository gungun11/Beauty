package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {"/pages/manager/*","/manager/bookServlet"})
public class ManagerFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 检查用户是否登录

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取用户登录之后的信息
        Object user = httpServletRequest.getSession().getAttribute("user");

        if (user == null) {
            // 用户还没有登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        } else {
            // 已经登录
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
