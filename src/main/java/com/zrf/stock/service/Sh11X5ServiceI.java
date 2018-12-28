package com.zrf.stock.service;

import com.zrf.stock.entity.Gd11X5Data;
import com.zrf.stock.entity.Sh11X5Data;

import java.util.List;

public interface Sh11X5ServiceI {
	public List<Sh11X5Data> getCurrentNum(String currentDay);
	public List<Sh11X5Data> getMiddleCount(String currentDay);
	public List<Sh11X5Data> getZ5(String currentDay);
	public List<Sh11X5Data> getBzList(String currentDay);
	public List<Sh11X5Data> getZs10List(String currentDay);
	public List<Sh11X5Data> getCountNum(String currentDay);
	public List<Sh11X5Data> getMiddleNum(String currentDay);
	public Sh11X5Data selectByPrimaryKey(String key);
	public int save(Sh11X5Data data);
}
