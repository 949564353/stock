package com.zrf.stock.service;

import com.zrf.stock.dao.Gd11X5DataMapper;
import com.zrf.stock.entity.Gd11X5Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Gd11X5ServiceImpl implements Gd11X5ServiceI {

	@Resource  
    public Gd11X5DataMapper gd11X5DataMapper;
	 
	@Override
	public List<Gd11X5Data> getCurrentNum(String currentDay){
		List<Gd11X5Data> data = gd11X5DataMapper.selectCurrentDayNums(currentDay);
		return data;
	}

	@Override
	public List<Gd11X5Data> getMiddleCount(String currentDay){
		List<Gd11X5Data> data = gd11X5DataMapper.getMiddleCount(currentDay);
		return data;
	}

	@Override
	public List<Gd11X5Data> getZ5(String currentDay){
		List<Gd11X5Data> data = gd11X5DataMapper.getZ5(currentDay);
		return data;
	}
	
	@Override
	public List<Gd11X5Data> getBzList(String currentDay){
		List<Gd11X5Data> data = gd11X5DataMapper.getBzList(currentDay);
		return data;
	}

	@Override
	public List<Gd11X5Data> getCountNum(String currentMonth){
		List<Gd11X5Data> data = gd11X5DataMapper.getCountNum(currentMonth);
		return data;
	}

	@Override
	public List<Gd11X5Data> getMiddleNum(String currentMonth){
		List<Gd11X5Data> data = gd11X5DataMapper.getMiddleNum(currentMonth);
		return data;
	}

	@Override
	public List<Gd11X5Data> getZs10List(String currentDay){
		List<Gd11X5Data> data = gd11X5DataMapper.getZs10List(currentDay);
		return data;
	}

	@Override
	public Gd11X5Data selectByPrimaryKey(String key){
		return gd11X5DataMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public int save(Gd11X5Data data){
		return gd11X5DataMapper.insert(data);
	}
	
      
}
