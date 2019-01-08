package com.zrf.stock.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrf.stock.dao.CqsscDataMapper;
import com.zrf.stock.dao.XjsscDataMapper;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.XjsscData;

@Service
public class XjsscServiceImpl implements XjsscServiceI {

	@Resource  
    public XjsscDataMapper xjsscMapper;  
	 
	@Override
	public List<XjsscData> getCurrentNum(String currentDay){
		List<XjsscData> data = xjsscMapper.selectCurrentDayNums(currentDay);
		return data;
	}
	
	@Override
	public List<XjsscData> getBzList(String currentDay){
		List<XjsscData> data = xjsscMapper.getBzList(currentDay);
		return data;
	}
	@Override
	public XjsscData selectByPrimaryKey(String key){
		return xjsscMapper.selectByPrimaryKey(key);
	}

	@Override
	public List<XjsscData> getAllData(){
		List<XjsscData> data = xjsscMapper.getAllData();
		return data;
	}

	@Override
	public List<XjsscData> getZsList(String currentDay){
		List<XjsscData> data = xjsscMapper.getZsList(currentDay);
		return data;
	}

	@Override
	public List<XjsscData> getZsNum(String currentMonth){
		List<XjsscData> data = xjsscMapper.getZsNum(currentMonth);
		return data;
	}

	@Override
	public List<XjsscData> getZs10List(String currentDay){
		List<XjsscData> data = xjsscMapper.getZs10List(currentDay);
		return data;
	}

	
	@Override
	public int save(XjsscData data){
		return xjsscMapper.insert(data);
	}
	
      
}
