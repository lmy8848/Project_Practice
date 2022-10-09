package com.javakc.cims.person.dao.impl;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.entity.Person;
import com.javakc.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:26
 * @description: [人员数据层实现]
 */
public class PersonDaoImpl implements PersonDao {

    private static PersonDaoImpl instance = null;

    private PersonDaoImpl() {
    }

    public static PersonDaoImpl getInstance() {
        if (instance == null) {
            synchronized (PersonDaoImpl.class) {
                if (instance == null) {
                    instance = new PersonDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int insert(Person entity) {
        return 0;
    }

    @Override
    public int update(Person entity) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int deletes(int[] ids) {
        return 0;
    }

    @Override
    public List<Person> queryAll() {
        SqlSession sqlSession = MybatisUtils.openSession(true);
        List<Person> list = sqlSession.selectList("person.queryAll");
        sqlSession.close();
        return list;
    }

    @Override
    public Person queryById(int id) {
        return null;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession session=MybatisUtils.openSession();
        long count=session.selectOne("person.queryByCount",params);
        session.close();
        return count;
    }

    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        SqlSession session= MybatisUtils.openSession();
        List<Person> list = session.selectList("person.queryByPage",params);
        session.close();
        return list;
    }
}