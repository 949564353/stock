package com.zrf.stock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrf.stock.dao.CqsscDataMapper;
import com.zrf.stock.entity.CqsscData;

@Service
public class CqsscServiceImpl implements CqsscServiceI {
	@Override
	public String getCurrentNum(){
		CqsscData data = cqsscMapper.selectByPrimaryKey(Integer.getInteger("1"));
		return "data";
	}
	
	@Autowired  
    private CqsscDataMapper cqsscMapper;  
      
}
