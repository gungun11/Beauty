package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void saveBook() {
        bookDao.saveBook(new Book(null,"骆驼祥子", "老舍",new BigDecimal(99),1000000,0, null));
    }


    @Test
    public void queryForPageCount(){
        System.out.println(bookDao.queryForPageCount());
    }


    @Test
    public void queryForPageCountByPrice(){
        System.out.println(bookDao.queryForPageCountByPrice(10,80));
    }

    @Test
    public void  queryForPageItemsByPrice(){
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,80)) {
            System.out.println(book);
        }
    }
    @Test
    public void  queryForPageItems(){
        for (Book book : bookDao.queryForPageItems(4, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }



    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"母猪产后护理", "猪大圣",new BigDecimal(9.9),10,11110, null) );
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(15));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}