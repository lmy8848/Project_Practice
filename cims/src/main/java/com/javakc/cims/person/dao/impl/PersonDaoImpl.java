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
        SqlSession session = MybatisUtils.openSession();
        int insert = session.insert("person.insert", entity);
        session.commit();
        session.close();
        return insert;
    }

    @Override
    public int update(Person entity) {
        SqlSession session = MybatisUtils.openSession(true);
        int update = session.update("person.update", entity);
        session.close();
        return update;
    }

    @Override
    public int delete(int id) {
        SqlSession session = MybatisUtils.openSession(true);
        int result = session.delete("person.delete", id);
        session.close();
        return result;
    }

    @Override
    public int deletes(int[] ids) {
        SqlSession session = MybatisUtils.openSession(true);
        int result = session.delete("person.deletes", ids);
        session.close();
        return result;
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
        SqlSession session = MybatisUtils.openSession();
        Person person = session.selectOne("person.queryById", id);
        session.close();
        return person;
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

    @Override
    public int queryByCode(String code) {
        SqlSession session = MybatisUtils.openSession();
        int result = session.selectOne("person.queryByCode",code);
        session.close();
        return result;
    }


}
