package com.javakc.cims.subsidy.dao;

import com.javakc.cims.subsidy.entity.Subsidy;

import java.util.List;
import java.util.Map;

public interface SubsidyDao {

    List<Subsidy> queryByPage(Map<String,Object> params);

    long queryByCount(int type);
    long queryByCount(Map<String,Object> params);
}
