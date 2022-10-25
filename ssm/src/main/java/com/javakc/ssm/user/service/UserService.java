package com.javakc.ssm.user.service;

import com.javakc.ssm.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * @author NJQ-PC
     * @date 2022/10/24 20:21
     * description:
     * @param entity:人员对象
     * @return 0失败 1成功
     */
    int insert(User entity);

    int update(User entity);

    int delete(int id);

    /**
     * @author NJQ-PC
     * @date 2022/10/24 20:23
     * description:  根据主键ID查询 返回人员对象
     * @param id:  com.javakc.ssm.user.entity.User
     */
    User queryById(int id);


    long queryByCount(Map<String,Object> params);


    List<User> queryByPage(Map<String,Object> params);
}
