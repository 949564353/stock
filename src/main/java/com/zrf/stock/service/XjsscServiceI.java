package com.zrf.stock.service;

import java.util.List;

import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.XjsscData;

public interface XjsscServiceI {
	public List<XjsscData> getCurrentNum(String currentDay);
	public List<XjsscData> getBzList(String currentDay);
	public XjsscData selectByPrimaryKey(String key);
	public int save(XjsscData data);
}
