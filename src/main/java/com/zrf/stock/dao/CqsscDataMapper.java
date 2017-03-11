package com.zrf.stock.dao;

import com.zrf.stock.entity.CqsscData;

public interface CqsscDataMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(CqsscData record);

    int insertSelective(CqsscData record);

    CqsscData selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(CqsscData record);

    int updateByPrimaryKey(CqsscData record);
}