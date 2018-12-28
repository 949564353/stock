package com.zrf.stock.dao;

import java.util.List;

import com.zrf.stock.entity.CqsscData;

public interface CqsscDataMapper {
	List<CqsscData> selectCurrentDayNums(String ID);
    List<CqsscData> getZ5(String ID);
	List<CqsscData> getBzList(String ID) ;
    List<CqsscData> getZs10List(String ID) ;
    List<CqsscData> getCountNum(String ID) ;
    List<CqsscData> getZsNum(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(CqsscData record);

    int insertSelective(CqsscData record);

    CqsscData selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(CqsscData record);

    int updateByPrimaryKey(CqsscData record);
}