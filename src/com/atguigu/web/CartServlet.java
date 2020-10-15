package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/cartServlet")
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数(如果有)
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2 调用bookService.queryBookById(id) : Book 调用商品编号
        Book book = bookService.queryBookById(id);
        //3 把book转换为CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4 获取Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //5 调用cart.addItem(cartItem)方法
        cart.addItem(cartItem);
        System.out.println(cart);

        // 保存到Session域中
        request.getSession().setAttribute("lastName", cartItem.getName());

        Map<String,Object> map = new HashMap<>();
        map.put("cartTotalCount",cart.getTotalCount());
        map.put("cartLastName",cartItem.getName());

        response.getWriter().write(new Gson().toJson(map));

    }


    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数(如果有)
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2 调用bookService.queryBookById(id) : Book 调用商品编号
        Book book = bookService.queryBookById(id);
        //3 把book转换为CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4 获取Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //5 调用cart.addItem(cartItem)方法
        cart.addItem(cartItem);
        System.out.println(cart);

        // 保存到Session域中
        request.getSession().setAttribute("lastName", cartItem.getName());

        //6 重定向回首页
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求参数
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        //2 获取购物车对象Cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            //3 调用Cart.deleteItem(id);
            cart.deleteItem(id);
            //4 重定向回购物车页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 有参数获取参数
        //2 获取Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            //3 调用cart.clear()方法清空购物车
            cart.clear();
            //4 重定向回购物车页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求参数 商品编号和商品数量
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        Integer count = WebUtils.parseInt(request.getParameter("count"),1);
        //2 获取Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            //3 调用cart.updateCount();
            cart.updateCount(id,count);
            //4 重定向回购物车页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


}
