package com.obitosnn.dao;

import com.obitosnn.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/10 22:38
 */
public abstract class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();
    private Class<T> tClass;
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] types = type.getActualTypeArguments();
        tClass = (Class<T>) types[0];
    }
    /**
     * 执行insert、delete、update SQL语句
     * @param sql
     * @param args
     * @return 更新数据的行数
     */
    public int update(String sql, Object ...args) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * @param sql
     * @param args
     * @param <T>
     * @return 查询一条记录
     */
    public T queryForOne(String sql, Object ...args) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanHandler<>(tClass), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     *  执行SQL语句返回多条就
     * @param sql SQL语句
     * @param args 填充占位符的可变形参
     * @param <T> 数据表对应的实体类
     * @return 执行SQL语句返回多条就
     */
    public List<T> queryForList(String sql, Object ...args) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanListHandler<>(tClass), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     *
     * @param sql
     * @param args
     * @return 返回查询结果只有一行一列的数据
     */
    public Object getValue(String sql, Object ...args) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
