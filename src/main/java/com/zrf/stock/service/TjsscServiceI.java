package com.zrf.stock.service;

import java.util.List;

import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.TjsscData;

public interface TjsscServiceI {
	public List<TjsscData> getCurrentNum(String currentDay);
	public List<TjsscData> getBzList(String currentDay);
	public TjsscData selectByPrimaryKey(String key);
	public int save(TjsscData data);
}
