package com.javakc.cims.person.factory;

import com.javakc.cims.person.dao.PersonDao;
import com.javakc.cims.person.dao.impl.PersonDaoImpl;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.person.service.impl.PersonServiceImpl;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:27
 * @description: [人员静态工厂]
 */
public class PersonFactory {
    /**
     * 获取人员模块逻辑层实现类
     *
     * @return 逻辑层实现类
     */
    public static PersonService getPersonService() {
        return new PersonServiceImpl();
    }

    /**
     * 获取人员模块数据层实现类
     *
     * @return 数据层实现类
     */
    public static PersonDao getPersonDao() {
        return PersonDaoImpl.getInstance();
    }
}
