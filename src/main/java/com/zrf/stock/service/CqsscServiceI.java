package com.zrf.stock.service;

import com.zrf.stock.entity.CqsscData;

public interface CqsscServiceI {
	public String getCurrentNum();
	public CqsscData selectByPrimaryKey(String key);
	public int save(CqsscData data);
}
