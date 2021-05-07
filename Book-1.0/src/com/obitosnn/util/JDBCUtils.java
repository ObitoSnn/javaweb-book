package com.obitosnn.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc工具类
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/10 22:25
 */
public abstract class JDBCUtils {
    private static DataSource dataSource;
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    /**
     * 初始化数据库连接池
     */
    static {
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pro = new Properties();
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws Exception {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            conn = dataSource.getConnection();//从数据库连接池中获取连接
            conn.setAutoCommit(false);//取消事务自动提交机制，手动管理事务
            connectionThreadLocal.set(conn);//保存至ThreadLocal中
        }
        return conn;
    }
    /**
     * 提交事务并关闭数据库连接
     */
    public static void commitAndCloseResource() {
        Connection conn = connectionThreadLocal.get();
        if (conn != null) {//之前用过该连接
            try {
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        //关闭连接之后一定要执行remove()，否则会出异常，Tomcat服务器底层使用线程池技术
        connectionThreadLocal.remove();
    }
    /**
     * 回滚事务并关闭数据库连接
     */
    public static void rollbackAndCloseResource() {
        Connection conn = connectionThreadLocal.get();
        if (conn != null) {//之前用过该连接
            try {
                conn.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        //关闭连接之后一定要执行remove()，否则会出异常，Tomcat服务器底层使用线程池技术
        connectionThreadLocal.remove();
    }

    /**
     * 关闭资源
     * @param conn
    public static void closeResource(Connection conn) {
        try {
            DbUtils.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
}
