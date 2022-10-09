package com.javakc.cims.person.dao;

import com.javakc.cims.person.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:25
 * @description: [人员数据层接口]
 */
public interface PersonDao {
    /**
     * 添加人员数据
     *
     * @param entity 实体
     * @return 成功/失败
     */
    public int insert(Person entity);

    /**
     * 修改人员数据
     *
     * @param entity 实体
     * @return 成功/失败
     */
    public int update(Person entity);

    /**
     * 删除人员数据
     *
     * @param id 主键
     * @return 成功/失败
     */
    public int delete(int id);

    /**
     * 批量删除人员数据
     *
     * @param ids 主键数组
     * @return 删除数据
     */
    public int deletes(int[] ids);

    /**
     * 查询全部数据
     *
     * @return 人员数据集合
     */
    @Deprecated
    public List<Person> queryAll();

    /**
     * 根据主键查询单条记录
     *
     * @param id 主键
     * @return 单条记录
     */
    public Person queryById(int id);

    /**
     * 根据条件查询总条数
     *
     * @param params 查询条件
     * @return 符合条件总条数
     */
    public long queryByCount(Map<String, Object> params);

    /**
     * 根据条件分页查询
     *
     * @param params 查询条件
     * @return 符合条件结果集
     */
    public List<Person> queryByPage(Map<String, Object> params);
}
