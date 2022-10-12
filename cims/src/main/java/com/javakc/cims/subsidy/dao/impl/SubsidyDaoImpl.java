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
}
