package com.zrf.stock.dao;

import java.util.List;

import com.zrf.stock.entity.TjsscData;

public interface TjsscDataMapper {
	List<TjsscData> selectCurrentDayNums(String ID);
	List<TjsscData> getBzList(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(TjsscData record);

    int insertSelective(TjsscData record);

    TjsscData selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(TjsscData record);

    int updateByPrimaryKey(TjsscData record);
}