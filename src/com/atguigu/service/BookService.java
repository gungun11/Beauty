package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {

    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 修改图书
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据主键删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 根据主键查询图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    public List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max);
}
