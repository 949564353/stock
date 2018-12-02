package com.zrf.stock.controller;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.zrf.stock.dao.CqsscDayMapper;
import com.zrf.stock.entity.CqsscDay;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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

	@Resource
	private CqsscDayMapper dayMapper;

	@RequestMapping(value="/getCurrentDay")
	@ResponseBody
	private String getCurrentNum(HttpServletRequest request){
		String selectDay = request.getParameter("selectDay");
		if(!StringUtils.isNotBlank(selectDay)){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
	        //时间解析    
	        selectDay = DateTime.now().toString(format);
		}
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
    		Integer bsgType = data.getBsgType();
    		Integer wxType = data.getWxType();
    		if(bsg!=null){
    			if(bsg.intValue()==1){
    				json.addProperty("bsg", "1");        		
    			}else if(bsg.intValue()==2){
    				json.addProperty("bsg", "2");
    			}else{
    				json.addProperty("bsg", "0");
    			}
    		}else{
    			json.addProperty("bsg", "0");
    		}
    		if(bsgType!=null){
    			json.addProperty("bsgType", bsgType);
    		}else{
    			json.addProperty("bsgType", "0");
    		}
    		
    		if(wxType!=null){
        		json.addProperty("wxType", wxType.toString());
    		}else{
        		json.addProperty("wxType", "0");
    		}
    		
    		if(list.size()==i+1){
    			lastNo = data.getDAY().substring(8, 11);
    		}
    		array.add(json);
        }

		int lastNum = 0;
		if(list.size()!=0){
			String lastNumStr = list.get(list.size()-1).getDAY().substring(8,11);
			lastNum = Integer.valueOf(lastNumStr);
		}

		//剩下的未开奖的数据
        for(int j=lastNum+1;j<=120;j++){
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
    		json.addProperty("bsgType", "    ");
    		json.addProperty("wxType", "    ");
    		array.add(json);
		}
        return array.toString();
	}


	@RequestMapping(value="/getZ5")
	@ResponseBody
	private String getZ5(HttpServletRequest request){
		String num = request.getParameter("num");
		List<CqsscData> list =  service.getZ5(num);
		JsonArray array = new JsonArray();
		Map<String,String> map = list.stream().collect(Collectors.toMap(CqsscData::getDAY,CqsscData::getNum));
		for(int i=1;i<121;i++){
			String numStr = i+"";
			if(i<10){
				numStr = "00"+i;
			}else if(i<100){
				numStr = "0"+i;
			}
			String count  = map.get(numStr);
			String countStr = StringUtils.isNotBlank(count)?count:"";

			JsonObject json = new JsonObject();
			json.addProperty("no", numStr);
			json.addProperty("num", countStr);
			array.add(json);
		}
		return array.toString();
	}
	
	
	@RequestMapping(value="/getBzList")
	@ResponseBody
	private String getBzList(HttpServletRequest request){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
        //时间解析    
        String currentDay = DateTime.now().toString(format);
        List<CqsscData> list =  service.getBzList(currentDay);
        JsonArray array = new JsonArray();
        for(int i=0;i<list.size();i++){
        	CqsscData data = list.get(i);
			System.out.println(data.getDAY()+"---"+currentDay);
			if(Integer.valueOf(data.getDAY()).intValue()<=Integer.valueOf(currentDay).intValue()) {
				System.out.println("======="+data.getDAY()+"---"+currentDay);
				JsonObject json = new JsonObject();
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
				json.addProperty("wxType", data.getWxType());
				json.addProperty("bsgType", data.getBsgType());
				json.addProperty("bz30", data.getZ30());
				json.addProperty("bz20", data.getZ20());
				array.add(json);
			}
        }
        return array.toString();
	}



	@RequestMapping(value="/getZs10List")
	@ResponseBody
	private String getZs10List(HttpServletRequest request){
		String selectDay = request.getParameter("selectDay");
		if(!StringUtils.isNotBlank(selectDay)){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
			//时间解析
			selectDay = DateTime.now().toString(format);
		}
		List<CqsscData> list =  service.getZs10List(selectDay);
		JsonArray array = new JsonArray();
		for(int i=0;i<list.size();i++){
			CqsscData data = list.get(i);
			JsonObject json = new JsonObject();
			json.addProperty("day", data.getDAY());
			json.addProperty("num", data.getNum());
			json.addProperty("num_w", data.getNumW());
			json.addProperty("num_q", data.getNumQ());
			json.addProperty("num_b", data.getNumB());
			json.addProperty("num_s", data.getNumS());
			json.addProperty("num_g", data.getNumG());
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
		return array.toString();
	}


	@RequestMapping(value="/getCountNum")
	@ResponseBody
	private String getCountNum(HttpServletRequest request){
		List<JSONObject> rtnList = new ArrayList<>();
		String selectMonth = request.getParameter("selectMonth");
		if(!StringUtils.isNotBlank(selectMonth)){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMM");
			//时间解析
			selectMonth = DateTime.now().toString(format);
		}
		List<CqsscData> list =  service.getCountNum(selectMonth);
		if(!CollectionUtils.isEmpty(list)){
			List z20List = list.stream().map(CqsscData::getZ20).collect(Collectors.toList());
			List z30List = list.stream().map(CqsscData::getZ30).collect(Collectors.toList());
			List dayList = list.stream().map(CqsscData::getDAY).collect(Collectors.toList());
			JSONObject object = new JSONObject();
			object.put("name","z20");
			object.put("color","red");
			object.put("data", z20List);
			rtnList.add(object);
			JSONObject object2 = new JSONObject();
			object2.put("name","z30");
			object2.put("color","#00B050");
			object2.put("data", z30List);
			rtnList.add(object2);

			JSONObject object3 = new JSONObject();
			object3.put("type","category");
			object3.put("data",dayList);
			rtnList.add(object3);
		}
		return rtnList.toString();
	}


	@RequestMapping(value="/addDay")
	@ResponseBody
	private void addDay(HttpServletRequest request){
		List<JSONObject> rtnList = new ArrayList<>();
		String beginDay = request.getParameter("beginDay");
		String endDay = request.getParameter("endDay");
		if(StringUtils.isNotBlank(beginDay) && StringUtils.isNotBlank(endDay) && Integer.valueOf(beginDay).intValue() < Integer.valueOf(endDay).intValue()){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
			DateTime beginDate = DateTime.parse(beginDay,format);
			DateTime endDate = DateTime.parse(endDay,format);
			while(beginDate.compareTo(endDate)<=0){
				String insertDate = endDate.toString(format);
				CqsscDay day = new CqsscDay();
				day.setDAY(insertDate);
				dayMapper.insert(day);
				endDate = endDate.minusDays(1);
			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String beginDay = "20180401";
		String endDay = "20180520";
		if(StringUtils.isNotBlank(beginDay) && StringUtils.isNotBlank(endDay) && Integer.valueOf(beginDay).intValue() < Integer.valueOf(endDay).intValue()){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
			DateTime beginDate = DateTime.parse(beginDay,format);
			DateTime endDate = DateTime.parse(endDay,format);
			while(beginDate.compareTo(endDate)<0){
				String insertDate = endDate.toString(format);
//				CqsscDay day = new CqsscDay();
//				day.setDAY(insertDate);
//				dayMapper.insert(day);
				System.out.println(insertDate);
				endDate = endDate.minusDays(1);
			}
		}

	}

}
