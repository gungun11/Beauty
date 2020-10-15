package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/manager/bookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    /*
    分页
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取请求的参数 pageNo 和 pageSize
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用bookServcie.page(pageNo,pageSize):Page对象;
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3 保存到Reqeust域中
        request.setAttribute("page",page);
        //4 请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        2 调用bookService.queryBooks()查询全部图书
        List<Book> books = bookService.queryBooks();
//        3 保存到request域中
        request.setAttribute("books", books);
//        4 请求转发到/pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取请求的参数 id
       Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        // 2 调用 bookService.deleteBookById()删除图书
        bookService.deleteBookById(id);
        // 3 重定向回图书列表管理页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取请求的参数 , 封装为book对象
        Book book = WebUtils.copyParamToBean(new Book(),request.getParameterMap());
        // 2 调用bookService.updateBook(book)修改图书
        bookService.updateBook(book);
        // 3 重定向到图书列表管理页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + request.getParameter("pageNo"));
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数封装为Book对象
        Book book = WebUtils.copyParamToBean(new Book(),request.getParameterMap());
        // 调用bookService.addBook(book)添加图书
        bookService.addBook(book);
//        // 调用list方法,查询并跳转到book_manager.jsp页面
//        list(request,response);
        // 使用重定向跳转到图书列表管理页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + Integer.MAX_VALUE);
    }

    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数 id
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        //2 调用bookService.queryBookById(id) : Book图书
        Book book = bookService.queryBookById(id);
        //3 保存修改的图书信息到Request域中
        request.setAttribute("book", book);
        //4 请求转发到 /pages/manager/book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }


}
