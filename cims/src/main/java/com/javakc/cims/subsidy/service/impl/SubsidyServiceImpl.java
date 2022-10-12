package com.javakc.cims.subsidy.service.impl;

import com.javakc.cims.person.dao.impl.PersonDaoImpl;
import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.dao.impl.SubsidyDaoImpl;
import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.subsidy.factory.SubsidyFactory;
import com.javakc.cims.subsidy.service.SubsidyService;

import java.util.List;
import java.util.Map;

public class SubsidyServiceImpl implements SubsidyService {
//
//    private SubsidyServiceImpl(){
//    }
//
//    private static volatile SubsidyServiceImpl instance=null;
//    public static SubsidyServiceImpl getInstance() {
//        if (instance==null){
//            synchronized (SubsidyServiceImpl.class) {
//                if (instance == null) {
//                    instance = new SubsidyServiceImpl();
//                }
//            }
//        }
//        return instance;
//    }
    /***
     * @author NJQ-PC
     * @date 2022/10/12 11:32
     * description: 获取数据层实现
     */

    private SubsidyDao subsidyDao= SubsidyFactory.getSubsidyDao();

    /**
     * @author NJQ-PC
     * @date 2022/10/12 11:33
     * description:分页查询
     * @param params:  java.util.List<com.javakc.cims.subsidy.entity.Subsidy>
     */

    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
        return subsidyDao.queryByPage(params);
    }

    /***
     * @author NJQ-PC
     * @date 2022/10/12 11:35
     * description:  查询数据库记录条目
     * @param type:  long
     */
    @Override
    public long queryByCount(int type) {
        return subsidyDao.queryByCount(type);
    }

    /**
     * @author NJQ-PC
     * @date 2022/10/12 20:15
     * description:  条件查询
     * @param params:  long
     */
    @Override
    public long queryByCount(Map<String, Object> params) {
        return subsidyDao.queryByCount(params);
    }
}
