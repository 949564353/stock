package com.zrf.stock.service;

import com.zrf.stock.entity.Gd11X5Data;

import java.util.List;

public interface Gd11X5ServiceI {
	public List<Gd11X5Data> getCurrentNum(String currentDay);
	public List<Gd11X5Data> getZ5(String currentDay);
	public List<Gd11X5Data> getBzList(String currentDay);
	public List<Gd11X5Data> getZs10List(String currentDay);
	public List<Gd11X5Data> getCountNum(String currentDay);
	public List<Gd11X5Data> getMiddleNum(String currentDay);
	public Gd11X5Data selectByPrimaryKey(String key);
	public int save(Gd11X5Data data);
}
