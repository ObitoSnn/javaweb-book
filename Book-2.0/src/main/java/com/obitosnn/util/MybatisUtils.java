package com.obitosnn.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/12/12 15:04
 */
public class MybatisUtils {
    private static SqlSessionFactory factory = null;
    private static final ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<SqlSession>();
    static {
        factory = (SqlSessionFactory) SpringUtils.getBean("factory");
    }

    /**
     * 获取非自动提交事务的SqlSession实例
     * @return
     */
    @Deprecated
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession == null) {
            //第一次获取SqlSession实例
            sqlSession = factory.openSession();
            //将SqlSession实例保存至ThreadLocal中，确保当前线程使用同一个SqlSession实例
            sqlSessionThreadLocal.set(sqlSession);
            return sqlSession;
        }
        return sqlSession;
    }
    /**
     * 根据autoCommit判断，获取的SqlSession实例是否自动提交事务，true表示自动提交事务，false表示不自动提交事务
     * @param autoCommit
     * @return
     */
    @Deprecated
    public static SqlSession getSqlSession(boolean autoCommit) {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession == null) {
            //第一次获取SqlSession实例
            sqlSession = factory.openSession();
            sqlSessionThreadLocal.set(sqlSession);
            return sqlSession;
        }
        return sqlSession;
    }

}
