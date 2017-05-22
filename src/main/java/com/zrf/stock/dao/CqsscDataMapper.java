package com.zrf.stock.dao;

import java.util.List;

import com.zrf.stock.entity.CqsscData;

public interface CqsscDataMapper {
	List<CqsscData> selectCurrentDayNums(String ID);
    int deleteByPrimaryKey(String ID);

    int insert(CqsscData record);

    int insertSelective(CqsscData record);

    CqsscData selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(CqsscData record);

    int updateByPrimaryKey(CqsscData record);
}