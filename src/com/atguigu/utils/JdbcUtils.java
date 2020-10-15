package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    static DruidDataSource dataSource;
    static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 从类路径下加载jdbc.properties属性配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载键值对数据
            properties.load(resourceAsStream);

            resourceAsStream.close();
            // 创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从数据库连接池中获取连接
     *
     * @return 返回null说明获取失败,有值就获取成功
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                // 从数据库连接池中获取连接
                conn = dataSource.getConnection();
                // 设置为手动提交事务
                conn.setAutoCommit(false);
                // 保存到ThreadLoccal中,共享
                conns.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose(){
        // 获取出之前使用的连接
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();//提交事务
                connection.close();//关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 一定要执行以下代码,否则就会报错
        conns.remove();
    }

    /**
     * 回滚事务并关闭连接
     */
    public static void rollbackAndClose(){
        // 获取出之前使用的连接
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.rollback();//回滚事务
                connection.close();//关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 一定要执行以下代码,否则就会报错
        conns.remove();
    }

//    /**
//     * 关闭连接,回放池中
//     *
//     * @param conn
//     */
//    public static void closeConnection(Connection conn) {
//        if (conn != null) {
//            try {
//                // 关闭连接,/放回池中
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
