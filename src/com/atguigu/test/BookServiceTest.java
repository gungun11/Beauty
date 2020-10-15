package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"明朝那些事","当年明月",new BigDecimal(89),10000,9,null));
    }


    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, Page.PAGE_SIZE,10,80);
        System.out.println(page);
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(2, Page.PAGE_SIZE);
        System.out.println(page);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"国哥在手,天下我有","0621",new BigDecimal(999),0,99999,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(3));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}