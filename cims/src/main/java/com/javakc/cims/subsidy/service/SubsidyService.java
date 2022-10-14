package com.javakc.cims.subsidy.service;

import com.javakc.cims.subsidy.entity.Subsidy;

import java.util.List;
import java.util.Map;

public interface SubsidyService {

    List<Subsidy> queryByPage(Map<String,Object> params);

    long queryByCount(int type);
    long queryByCount(Map<String,Object> params);
    int delete(int id);

    int deletes(int[] ids);

    Subsidy queryById(int id);

    int update(Subsidy subsidy);
}
