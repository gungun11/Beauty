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

@WebServlet(value = "/client/bookServlet")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取请求的参数 pageNo 和 pageSize
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用bookServcie.page(pageNo,pageSize):Page对象;
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        //3 保存到Reqeust域中
        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }


    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取请求的参数 pageNo 和 pageSize
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Integer min = WebUtils.parseInt(request.getParameter("min"), 0);
        Integer max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2 调用bookServcie.page(pageNo,pageSize):Page对象;
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        String minStr = request.getParameter("min");
        if (minStr != null) {
            sb.append("&min=").append(minStr);
        }

        String maxStr = request.getParameter("max");
        if (maxStr != null) {
            sb.append("&max=").append(maxStr);
        }

        page.setUrl(sb.toString());

        //3 保存到Reqeust域中
        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }


}
