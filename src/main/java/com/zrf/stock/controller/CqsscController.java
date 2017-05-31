package com.zrf.stock.controller;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.service.CqsscServiceI;

@Controller
@RequestMapping(value="/cqssc")
public class CqsscController {
	
	@Resource
	private CqsscServiceI service;
	
	@RequestMapping(value="/getCurrentDay", produces="text/html;charset=UTF-8")
	@ResponseBody
	private String getCurrentNum(){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
        //时间解析    
        //String currentDay = DateTime.now().toString(format);
        String currentDay = "20170531";
        List<CqsscData> list =  service.getCurrentNum(currentDay);
        JsonArray array = new JsonArray();
        for(CqsscData data:list){
    		JsonObject json = new JsonObject();
    		json.addProperty("no", data.getNum());
    		json.addProperty("num", data.getNum());
    		json.addProperty("bsg", data.getIsBsg());
    		array.add(json);
        }
        return array.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
