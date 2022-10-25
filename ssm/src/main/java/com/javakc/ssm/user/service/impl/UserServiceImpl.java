package com.javakc.ssm.user.service.impl;

import com.javakc.ssm.user.dao.UserDao;
import com.javakc.ssm.user.entity.User;
import com.javakc.ssm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public int insert(User entity) {
        return  userDao.insert(entity);
    }

    @Override
    @Transactional
    public int update(User entity) {
        return userDao.update(entity);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return userDao.delete(id);
    }

    @Override
    @Transactional
    public User queryById(int id) {
        return userDao.queryById(id);
    }

    @Override
    @Transactional
    public long queryByCount(Map<String, Object> params) {
        return userDao.queryByCount(params);
    }

    @Override
    @Transactional
    public List<User> queryByPage(Map<String, Object> params) {
        return userDao.queryByPage(params);
    }
}
