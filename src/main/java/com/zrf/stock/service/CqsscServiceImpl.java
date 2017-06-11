package com.zrf.stock.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrf.stock.dao.CqsscDataMapper;
import com.zrf.stock.entity.CqsscData;

@Service
public class CqsscServiceImpl implements CqsscServiceI {

	@Resource  
    public CqsscDataMapper cqsscMapper;  
	 
	@Override
	public List<CqsscData> getCurrentNum(String currentDay){
		List<CqsscData> data = cqsscMapper.selectCurrentDayNums(currentDay);
		return data;
	}
	
	@Override
	public List<CqsscData> getBzList(String currentDay){
		List<CqsscData> data = cqsscMapper.getBzList(currentDay);
		return data;
	}
	@Override
	public CqsscData selectByPrimaryKey(String key){
		return cqsscMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public int save(CqsscData data){
		return cqsscMapper.insert(data);
	}
	
      
}
