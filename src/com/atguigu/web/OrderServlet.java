package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/orderServlet")
public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(" OrderServlet 线程名是 => " + Thread.currentThread().getName());

        // 获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 获取用户编号
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Integer userId = user.getId();
        // 调用OrderService.createOrder(用户编号，购物车);
        String orderId = null;

        orderId = orderService.createOrder(userId, cart);

        // 保存订单号到request域中
        request.getSession().setAttribute("orderId", orderId);
        // 页面跳转 ===>>>>   /pages/cart/checkout.jsp
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }


}
