package com.zrf.stock.service;

import com.zrf.stock.dao.Sh11X5DataMapper;
import com.zrf.stock.entity.Sh11X5Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Sh11X5ServiceImpl implements Sh11X5ServiceI {

	@Resource  
    public Sh11X5DataMapper sh11X5DataMapper;
	 
	@Override
	public List<Sh11X5Data> getCurrentNum(String currentDay){
		List<Sh11X5Data> data = sh11X5DataMapper.selectCurrentDayNums(currentDay);
		return data;
	}

	@Override
	public List<Sh11X5Data> getZ5(String currentDay){
		List<Sh11X5Data> data = sh11X5DataMapper.getZ5(currentDay);
		return data;
	}
	
	@Override
	public List<Sh11X5Data> getBzList(String currentDay){
		List<Sh11X5Data> data = sh11X5DataMapper.getBzList(currentDay);
		return data;
	}

	@Override
	public List<Sh11X5Data> getCountNum(String currentMonth){
		List<Sh11X5Data> data = sh11X5DataMapper.getCountNum(currentMonth);
		return data;
	}

	@Override
	public List<Sh11X5Data> getMiddleNum(String currentMonth){
		List<Sh11X5Data> data = sh11X5DataMapper.getMiddleNum(currentMonth);
		return data;
	}

	@Override
	public List<Sh11X5Data> getZs10List(String currentDay){
		List<Sh11X5Data> data = sh11X5DataMapper.getZs10List(currentDay);
		return data;
	}

	@Override
	public Sh11X5Data selectByPrimaryKey(String key){
		return sh11X5DataMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public int save(Sh11X5Data data){
		return sh11X5DataMapper.insert(data);
	}
	
      
}
