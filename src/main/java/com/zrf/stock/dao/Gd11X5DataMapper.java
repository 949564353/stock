package com.zrf.stock.dao;

import com.zrf.stock.entity.Gd11X5Data;

import java.util.List;

public interface Gd11X5DataMapper {
	List<Gd11X5Data> selectCurrentDayNums(String ID);
    List<Gd11X5Data> getZ5(String ID);
	List<Gd11X5Data> getBzList(String ID) ;
    List<Gd11X5Data> getZs10List(String ID) ;
    List<Gd11X5Data> getCountNum(String ID) ;
    List<Gd11X5Data> getMiddleNum(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(Gd11X5Data record);

    int insertSelective(Gd11X5Data record);

    Gd11X5Data selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(Gd11X5Data record);

    int updateByPrimaryKey(Gd11X5Data record);
}