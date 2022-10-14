package com.javakc.cims.subsidy.dao.impl;

import com.javakc.cims.person.dao.impl.PersonDaoImpl;
import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class SubsidyDaoImpl implements SubsidyDao {

    private SubsidyDaoImpl(){

    }
    private static volatile SubsidyDaoImpl instance=null;

    public static SubsidyDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SubsidyDaoImpl.class) {
                if (instance == null) {
                    instance = new SubsidyDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
        SqlSession session = MybatisUtils.openSession();
        List<Subsidy> result = session.selectList("subsidy.queryByPage", params);
        session.close();
        return result;
    }

    @Override
    public long queryByCount(int type) {
        SqlSession session = MybatisUtils.openSession();
        long result = session.selectOne("subsidy.queryByCount", type);
        session.close();
        return result;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession session = MybatisUtils.openSession();
        long result = session.selectOne("subsidy.queryByCount", params);
        session.close();
        return result;
    }

    @Override
    public int delete(int id) {
        SqlSession session = MybatisUtils.openSession(true);
        int result = session.delete("subsidy.delete", id);
        session.close();
        return result;
    }

    @Override
    public int deletes(int[] ids) {
        SqlSession session = MybatisUtils.openSession(true);
        int delete = session.delete("subsidy.deletes",ids);
        session.close();
        return delete;
    }

    @Override
    public Subsidy queryById(int id) {
        SqlSession session = MybatisUtils.openSession();
        Subsidy result = session.selectOne("subsidy.queryById", id);
        session.close();
        return result;
    }

    @Override
    public int update(Subsidy subsidy) {
        SqlSession session = MybatisUtils.openSession(true);
        int result = session.update("subsidy.update", subsidy);
        session.close();
        return result;
    }

    @Override
    public List<Map<String, Object>> querySubsidy(int type) {
        SqlSession session = MybatisUtils.openSession();
        List<Map<String,Object>> result = session.selectList("subsidy.querySubsidy");
        session.close();
        return result;
    }
}
