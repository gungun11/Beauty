package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(value = "/userServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 验证用户名是否可用
     */
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数
        String username = request.getParameter("username");
        //2 调用userService.existsUsername(username):boolean;
        boolean existsUsername = userService.existsUsername(username);
        //3 返回验证的结果
        Map<String,Object> result = new HashMap<>();
        result.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(result));

    }


    /**
     * 注销登录
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1 销毁Session / 删除Session中登录的信息
        request.getSession().invalidate();
//        2 重定向到首页( 登录页面 )
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 表示登录的业务
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        1 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        2 调用XxxService.login():User;
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            //        3 比较判断,user == null
//                登录失败,用户名或密码错误
            System.out.println("用户名或密码错误,登录失败");
            // 保存错误信息,和回显的信息
            request.setAttribute("username",username);
            request.setAttribute("msg","用户名或密码错误");

//        跳转登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        } else {
            //      不等于null
            System.out.println("登录成功");

            // 保存用户登录之后的信息,到Session域中
            request.getSession().setAttribute("user",loginUser);

//        登录成功,跳到登录成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    /**
     * 表示注册的业务
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Constants.KAPTCHA_SESSION_KEY就是谷歌验证码jar包里,保存验证码的key
         */
        String token = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        //        1 获取请求的参数,
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        /**
         * populate()方法可以把map中的数据直接注入到Bean的属性中
         * 第一个参数是Bean对象
         * 第二个参数是map对象
         * name=value&name=value
         */
        User user = WebUtils.copyParamToBean(new User(),request.getParameterMap());
        System.out.println(" 注入之后 =>  " + user);

//                2 检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            //                正确
            if (userService.existsUsername(username)) {
                //        不可用
                System.out.println("用户名[" + username + "]已存在");
                request.setAttribute("msg", "用户名已存在!");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                //                跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            } else {
                //        3 检查用户名是否可用
                //                可用
                //        保存到数据库
                userService.registUser(new User(null,username,password,email));
                //                跳转到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        } else {
            //        不正确
            System.out.println("验证码 : " + code + " , 不正确");
            request.setAttribute("msg", "验证码不正确!");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            //                跳回注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }




}
