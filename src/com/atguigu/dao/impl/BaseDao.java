package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 定义大部分的相同的公共代码.执行CRUD操作的代码.
 */
public abstract class BaseDao {

    // 使用的DBUtils
    /**
     * QueryRunner类主要用于执行sql语句,insert,delete,update,select.
     */
    QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行insert , update , delete 的sql语句
     *
     * @param sql  执行的sql语句
     * @param args sql语句对应的参数值
     * @return 影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {

            /**
             * 执行insert , delete ,update 的sql语句
             */
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一个对象
     *
     * @param type 返回的对象类型
     * @param sql  sql语句
     * @param args sql的参数
     * @param <T>  返回的具体泛型类型
     * @return 返回 null ,说明查询失败,有值就成功
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            /**
             *  ResultSetHandler 接口实现类.将查询到的ResultSet结果集转换为需要的类型 <br/>
             *  BeanHandler是将查询的结果转换为一个Bean对象 <br/>
             *  BeanListHandler是将查询的结果转换为多个Bean对象(并存在list集合中) <br/>
             *  ScalarHandler将查询回来一行一列的数据转换为具体的返回值 <br/>
             */
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询多个对象
     *
     * @param type 返回的对象类型
     * @param sql  sql语句
     * @param args sql的参数
     * @param <T>  返回的具体泛型类型
     * @return 返回 null ,说明查询失败,有值就成功
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            /**
             *  ResultSetHandler 接口实现类.将查询到的ResultSet结果集转换为需要的类型 <br/>
             *  BeanHandler是将查询的结果转换为一个Bean对象 <br/>
             *  BeanListHandler是将查询的结果转换为多个Bean对象(并存在list集合中) <br/>
             *  ScalarHandler将查询回来一行一列的数据转换为具体的返回值 <br/>
             */
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 执行统计查询
     *
     * @param sql  sql语句
     * @param args sql的参数
     * @return 返回 null ,说明查询失败,有值就成功
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            /**
             *  ResultSetHandler 接口实现类.将查询到的ResultSet结果集转换为需要的类型 <br/>
             *  BeanHandler 是将查询的结果转换为一个Bean对象 <br/>
             *  BeanListHandler 是将查询的结果转换为多个Bean对象(并存在list集合中) <br/>
             *  ScalarHandler 将查询回来一行一列的数据转换为具体的返回值 <br/>
             */
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
