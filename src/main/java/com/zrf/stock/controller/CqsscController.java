package com.zrf.stock.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
	private String getCurrentNum(HttpServletRequest request){
		String selectDay = request.getParameter("selectDay");
		if(!StringUtils.isNotBlank(selectDay)){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
	        //时间解析    
	        selectDay = DateTime.now().toString(format);
		}
        //String currentDay = "20170531";
        List<CqsscData> list =  service.getCurrentNum(selectDay);
        JsonArray array = new JsonArray();
        String lastNo = "";
        for(int i=0;i<list.size();i++){
        	CqsscData data = list.get(i);
    		JsonObject json = new JsonObject();
    		json.addProperty("day", data.getDAY().substring(0,8));
    		json.addProperty("no", data.getDAY().substring(8, 11));
    		json.addProperty("num", data.getNum());
    		Integer bsg = data.getIsBsg();
    		if(bsg!=null){
    			if(bsg.intValue()==1){
    				json.addProperty("bsg", "组三");        		
    			}else if(bsg.intValue()==2){
    				json.addProperty("bsg", "豹子");
    			}else{
    				json.addProperty("bsg", "组六");
    			}
    		}else{
    			json.addProperty("bsg", "组六");
    		}
    		
    		if(list.size()==i+1){
    			lastNo = data.getDAY().substring(8, 11);
    		}
    		array.add(json);
        }

		//剩下的未开奖的数据
        for(int j=list.size()+1;j<=120;j++){
    		JsonObject json = new JsonObject();
			if(j<10){
	    		json.addProperty("no", "00"+j);
			}else if(j<100){
	    		json.addProperty("no", "0"+j);
			}else{
	    		json.addProperty("no", j);
			}
			json.addProperty("day", selectDay);
    		json.addProperty("num", "      ");
    		json.addProperty("bsg", "    ");
    		array.add(json);
		}
        return array.toString();
	}
	
	
	
	@RequestMapping(value="/getBzList", produces="text/html;charset=UTF-8")
	@ResponseBody
	private String getBzList(HttpServletRequest request){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
        //时间解析    
        String currentDay = DateTime.now().toString(format);
        List<CqsscData> list =  service.getBzList(currentDay);
        JsonArray array = new JsonArray();
        for(int i=0;i<list.size();i++){
        	CqsscData data = list.get(i);
    		JsonObject json = new JsonObject();
    		if(Integer.valueOf(data.getDAY())<=Integer.valueOf(currentDay)){
    			json.addProperty("day", data.getDAY());
        		json.addProperty("isWqb", data.getIsWqb());
        		json.addProperty("isWqs", data.getIsWqs());
        		json.addProperty("isWqg", data.getIsWqg());
        		json.addProperty("isWbs", data.getIsWbs());
        		json.addProperty("isWbg", data.getIsWbg());
        		json.addProperty("isWsg", data.getIsWsg());
        		json.addProperty("isQbs", data.getIsQbs());
        		json.addProperty("isQbg", data.getIsQbg());
        		json.addProperty("isQsg", data.getIsQsg());
        		json.addProperty("isBsg", data.getIsBsg());
        		array.add(json);
    		}
        }
        return array.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
