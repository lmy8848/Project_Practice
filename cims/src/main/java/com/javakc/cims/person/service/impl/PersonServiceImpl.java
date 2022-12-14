package com.javakc.cims.person.service.impl;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.entity.Person;
import com.javakc.cims.person.factory.PersonFactory;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:27
 * @description: [人员逻辑层实现]
 */
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao = PersonFactory.getPersonDao();

    @Override
    public int insert(Person entity) {
        return personDao.insert(entity);
    }

    @Override
    public int update(Person entity) {
        return personDao.update(entity);
    }

    @Override
    public int delete(int id) {
        return personDao.delete(id);
    }

    @Override
    public int deletes(int[] ids) {
        return personDao.deletes(ids);
    }

    @Override
    public List<Person> queryAll() {
        return personDao.queryAll();
    }

    @Override
    public Person queryById(int id) {
        return personDao.queryById(id);
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        return personDao.queryByCount(params);
    }

    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        return personDao.queryByPage(params);
    }

    @Override
    public boolean queryByCode(String code) {
        return personDao.queryByCode(code) == 0;
    }
}
