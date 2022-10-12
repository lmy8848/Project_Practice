package com.javakc.cims.subsidy.factory;

import com.javakc.cims.subsidy.dao.SubsidyDao;
import com.javakc.cims.subsidy.dao.impl.SubsidyDaoImpl;
import com.javakc.cims.subsidy.service.SubsidyService;
import com.javakc.cims.subsidy.service.impl.SubsidyServiceImpl;

public class SubsidyFactory {

    /**
     * @author NJQ-PC
     * @date 2022/10/12 11:33
     * description:   com.javakc.cims.subsidy.service.SubsidyService
     */
    public static SubsidyService getSubsidyService(){
        return new SubsidyServiceImpl();
    }

    /***
     * @author NJQ-PC
     * @date 2022/10/12 11:33
     * description:   com.javakc.cims.subsidy.dao.SubsidyDao
     */
    public static SubsidyDao getSubsidyDao(){
        return SubsidyDaoImpl.getInstance();
    }
}
