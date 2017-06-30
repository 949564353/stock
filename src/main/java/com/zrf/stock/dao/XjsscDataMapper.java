package com.zrf.stock.dao;

import java.util.List;

import com.zrf.stock.entity.XjsscData;

public interface XjsscDataMapper {
	List<XjsscData> selectCurrentDayNums(String ID);
	List<XjsscData> getBzList(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(XjsscData record);

    int insertSelective(XjsscData record);

    XjsscData selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(XjsscData record);

    int updateByPrimaryKey(XjsscData record);
}