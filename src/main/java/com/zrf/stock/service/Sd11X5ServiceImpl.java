package com.zrf.stock.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.zrf.stock.entity.Sd11X5Data;
import com.zrf.stock.dao.Sd11X5DataMapper;import java.util.List;


@Service
public class Sd11X5ServiceImpl implements Sd11X5ServiceI {

	@Resource  
    public Sd11X5DataMapper sd11X5DataMapper;
	 
	@Override
	public List<Sd11X5Data> getCurrentNum(String currentDay){
		List<Sd11X5Data> data = sd11X5DataMapper.selectCurrentDayNums(currentDay);
		return data;
	}

	@Override
	public List<Sd11X5Data> getZ5(String currentDay){
		List<Sd11X5Data> data = sd11X5DataMapper.getZ5(currentDay);
		return data;
	}
	
	@Override
	public List<Sd11X5Data> getBzList(String currentDay){
		List<Sd11X5Data> data = sd11X5DataMapper.getBzList(currentDay);
		return data;
	}

	@Override
	public List<Sd11X5Data> getCountNum(String currentMonth){
		List<Sd11X5Data> data = sd11X5DataMapper.getCountNum(currentMonth);
		return data;
	}

	@Override
	public List<Sd11X5Data> getMiddleNum(String currentMonth){
		List<Sd11X5Data> data = sd11X5DataMapper.getMiddleNum(currentMonth);
		return data;
	}

	@Override
	public List<Sd11X5Data> getZs10List(String currentDay){
		List<Sd11X5Data> data = sd11X5DataMapper.getZs10List(currentDay);
		return data;
	}

	@Override
	public Sd11X5Data selectByPrimaryKey(String key){
		return sd11X5DataMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public int save(Sd11X5Data data){
		return sd11X5DataMapper.insert(data);
	}
	
      
}
