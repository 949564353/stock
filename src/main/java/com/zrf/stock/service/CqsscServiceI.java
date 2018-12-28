package com.zrf.stock.service;

import java.util.List;

import com.zrf.stock.entity.CqsscData;

public interface CqsscServiceI {
	public List<CqsscData> getCurrentNum(String currentDay);
	public List<CqsscData> getZ5(String currentDay);
	public List<CqsscData> getBzList(String currentDay);
	public List<CqsscData> getZs10List(String currentDay);
	public List<CqsscData> getCountNum(String currentDay);
	public List<CqsscData> getZsNum(String currentDay);
	public CqsscData selectByPrimaryKey(String key);
	public int save(CqsscData data);
}
