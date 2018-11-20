package com.zrf.stock.dao;

import java.util.List;

import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.XjsscData;

public interface XjsscDataMapper {
	List<XjsscData> selectCurrentDayNums(String ID);
	List<XjsscData> getBzList(String ID) ;
    List<XjsscData> getAllData() ;
    int deleteByPrimaryKey(String ID);

    int insert(XjsscData record);

    int insertSelective(XjsscData record);

    XjsscData selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(XjsscData record);

    List<XjsscData> getZs10List(String ID) ;

    int updateByPrimaryKey(XjsscData record);
}