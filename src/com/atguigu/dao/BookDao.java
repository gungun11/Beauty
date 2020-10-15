package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {


    /**
     * 保存图书
     *
     * @param book
     * @return
     */
    public int saveBook(Book book);

    /**
     * 修改图书
     *
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 根据主键删除图书
     *
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 根据主键查询图书
     *
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部的图书
     *
     * @return
     */
    public List<Book> queryBooks();

    Integer queryForPageCount();

    List<Book> queryForPageItems(Integer begin, Integer pageSize);

    Integer queryForPageCountByPrice(Integer min, Integer max);

    List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max);
}
