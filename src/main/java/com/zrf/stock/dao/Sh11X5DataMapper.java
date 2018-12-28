package com.zrf.stock.dao;

import com.zrf.stock.entity.Gd11X5Data;
import com.zrf.stock.entity.Sh11X5Data;

import java.util.List;

public interface Sh11X5DataMapper {
	List<Sh11X5Data> selectCurrentDayNums(String ID);
    List<Sh11X5Data> getMiddleCount(String ID);
    List<Sh11X5Data> getZ5(String ID);
	List<Sh11X5Data> getBzList(String ID) ;
    List<Sh11X5Data> getZs10List(String ID) ;
    List<Sh11X5Data> getCountNum(String ID) ;
    List<Sh11X5Data> getMiddleNum(String ID) ;
    int deleteByPrimaryKey(String ID);

    int insert(Sh11X5Data record);

    int insertSelective(Sh11X5Data record);

    Sh11X5Data selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(Sh11X5Data record);

    int updateByPrimaryKey(Sh11X5Data record);
}