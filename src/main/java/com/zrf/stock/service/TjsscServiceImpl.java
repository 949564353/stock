package com.zrf.stock.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrf.stock.dao.CqsscDataMapper;
import com.zrf.stock.dao.TjsscDataMapper;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.TjsscData;

@Service
public class TjsscServiceImpl implements TjsscServiceI {

	@Resource  
    public TjsscDataMapper tjsscMapper;  
	 
	@Override
	public List<TjsscData> getCurrentNum(String currentDay){
		List<TjsscData> data = tjsscMapper.selectCurrentDayNums(currentDay);
		return data;
	}
	
	@Override
	public List<TjsscData> getBzList(String currentDay){
		List<TjsscData> data = tjsscMapper.getBzList(currentDay);
		return data;
	}
	@Override
	public TjsscData selectByPrimaryKey(String key){
		return tjsscMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public int save(TjsscData data){
		return tjsscMapper.insert(data);
	}
	
      
}
