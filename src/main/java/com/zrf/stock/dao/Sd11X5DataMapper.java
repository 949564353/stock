package com.zrf.stock.dao;

import com.zrf.stock.entity.Gd11X5Data;
import com.zrf.stock.entity.Sd11X5Data;

import java.util.List;

public interface Sd11X5DataMapper {
	List<Sd11X5Data> selectCurrentDayNums(String ID);
    List<Sd11X5Data> getMiddleCount(String ID);
    List<Sd11X5Data> getZ5(String ID);
	List<Sd11X5Data> getBzList(String ID) ;
    List<Sd11X5Data> getZs10List(String ID) ;
    List<Sd11X5Data> getCountNum(String ID) ;
    List<Sd11X5Data> getMiddleNum(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(Sd11X5Data record);

    int insertSelective(Sd11X5Data record);

    Sd11X5Data selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(Sd11X5Data record);

    int updateByPrimaryKey(Sd11X5Data record);
}