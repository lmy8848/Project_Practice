package com.javakc.cims.util.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:32
 * @description: [mybatis工具类]
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //1.读取mybatis配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2.构建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MybatisUtils() {
    }

    /**
     * 手动提交会话
     *
     * @return SqlSession
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 获取会话对象
     *
     * @param autoCommit true 自动提交
     *                   false 手动提交
     * @return SqlSession
     */
    public static SqlSession openSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    public static SqlSession openSession(ExecutorType type) {
        return sqlSessionFactory.openSession(type);
    }

}
