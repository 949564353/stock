package com.zrf.stock.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zrf.stock.dao.CqsscDayMapper;
import com.zrf.stock.dao.Sd11X5DataMapper;
import com.zrf.stock.entity.Sd11X5Data;
import com.zrf.stock.service.Sd11X5ServiceI;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value="/sd11x5")
public class Sd11x5Controller {
	
	@Resource
	private Sd11X5ServiceI service;

	@Resource
	public Sd11X5DataMapper mapper;

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
        List<Sd11X5Data> list =  service.getCurrentNum(selectDay);
        JsonArray array = new JsonArray();
        for(int i=0;i<list.size();i++){
			Sd11X5Data data = list.get(i);
    		JsonObject json = new JsonObject();
    		json.addProperty("day", data.getDAY().substring(0,8));
    		json.addProperty("no", data.getDAY().substring(8, 10));
    		json.addProperty("num", data.getNum());
			json.addProperty("odd", data.getOddNum());
			json.addProperty("even", data.getEvenNum());
			json.addProperty("middle", data.getNumMiddle());
    		array.add(json);
        }

		int lastNum = 0;
		if(list.size()!=0){
			String lastNumStr = list.get(list.size()-1).getDAY().substring(8,10);
			lastNum = Integer.valueOf(lastNumStr);
		}

		//剩下的未开奖的数据
        for(int j=lastNum+1;j<=87;j++){
    		JsonObject json = new JsonObject();
			if(j<10){
	    		json.addProperty("no", "0"+j);
			}else{
				json.addProperty("no",j);
			}
			json.addProperty("day", selectDay);
    		json.addProperty("num", "");
    		json.addProperty("odd", "");
    		json.addProperty("even", "");
			json.addProperty("middle", "");
    		array.add(json);
		}
        return array.toString();
	}


//	@RequestMapping(value="/getZ5")
//	@ResponseBody
//	private String getZ5(HttpServletRequest request){
//		String num = request.getParameter("num");
//		List<CqsscData> list =  service.getZ5(num);
//		JsonArray array = new JsonArray();
//		Map<String,String> map = list.stream().collect(Collectors.toMap(CqsscData::getDAY,CqsscData::getNum));
//		for(int i=1;i<121;i++){
//			String numStr = i+"";
//			if(i<10){
//				numStr = "00"+i;
//			}else if(i<100){
//				numStr = "0"+i;
//			}
//			String count  = map.get(numStr);
//			String countStr = StringUtils.isNotBlank(count)?count:"";
//
//			JsonObject json = new JsonObject();
//			json.addProperty("no", numStr);
//			json.addProperty("num", countStr);
//			array.add(json);
//		}
//		return array.toString();
//	}
//
//
	@RequestMapping(value="/getBzList")
	@ResponseBody
	private String getBzList(HttpServletRequest request){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
        //时间解析
        String currentDay = DateTime.now().toString(format);
        List<Sd11X5Data> list =  service.getBzList(currentDay);
        JsonArray array = new JsonArray();
        for(int i=0;i<list.size();i++){
			Sd11X5Data data = list.get(i);
			System.out.println(data.getDAY()+"---"+currentDay);
			if(Integer.valueOf(data.getDAY()).intValue()<=Integer.valueOf(currentDay).intValue()) {
				System.out.println("======="+data.getDAY()+"---"+currentDay);
				JsonObject json = new JsonObject();
				json.addProperty("day", data.getDAY());
				json.addProperty("zero", data.getZero());
				json.addProperty("one", data.getOne());
				json.addProperty("four", data.getFour());
				json.addProperty("five", data.getFive());
				array.add(json);
			}
        }
        return array.toString();
	}


//
//	@RequestMapping(value="/getZs10List")
//	@ResponseBody
//	private String getZs10List(HttpServletRequest request){
//		String selectDay = request.getParameter("selectDay");
//		if(!StringUtils.isNotBlank(selectDay)){
//			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
//			//时间解析
//			selectDay = DateTime.now().toString(format);
//		}
//		List<CqsscData> list =  service.getZs10List(selectDay);
//		JsonArray array = new JsonArray();
//		for(int i=0;i<list.size();i++){
//			CqsscData data = list.get(i);
//			JsonObject json = new JsonObject();
//			json.addProperty("day", data.getDAY());
//			json.addProperty("num", data.getNum());
//			json.addProperty("num_w", data.getNumW());
//			json.addProperty("num_q", data.getNumQ());
//			json.addProperty("num_b", data.getNumB());
//			json.addProperty("num_s", data.getNumS());
//			json.addProperty("num_g", data.getNumG());
//			json.addProperty("isWqb", data.getIsWqb());
//			json.addProperty("isWqs", data.getIsWqs());
//			json.addProperty("isWqg", data.getIsWqg());
//			json.addProperty("isWbs", data.getIsWbs());
//			json.addProperty("isWbg", data.getIsWbg());
//			json.addProperty("isWsg", data.getIsWsg());
//			json.addProperty("isQbs", data.getIsQbs());
//			json.addProperty("isQbg", data.getIsQbg());
//			json.addProperty("isQsg", data.getIsQsg());
//			json.addProperty("isBsg", data.getIsBsg());
//			array.add(json);
//		}
//		return array.toString();
//	}
//
//
	@RequestMapping(value="/getMiddleNum")
	@ResponseBody
	private String getCountNum(HttpServletRequest request){
		List<Sd11X5Data> list =  service.getMiddleNum("");
		if(!CollectionUtils.isEmpty(list)){
			for(Sd11X5Data data:list){
				List<Integer> numList = new ArrayList<>();
				numList.add(Integer.valueOf(data.getNumW()));
				numList.add(Integer.valueOf(data.getNumQ()));
				numList.add(Integer.valueOf(data.getNumB()));
				numList.add(Integer.valueOf(data.getNumS()));
				numList.add(Integer.valueOf(data.getNumG()));
				Collections.sort(numList);
				data.setNumMiddle(numList.get(2).toString());
				mapper.updateByPrimaryKey(data);
			}
		}
		return "";
	}
//
//
//	@RequestMapping(value="/addDay")
//	@ResponseBody
//	private void addDay(HttpServletRequest request){
//		List<JSONObject> rtnList = new ArrayList<>();
//		String beginDay = request.getParameter("beginDay");
//		String endDay = request.getParameter("endDay");
//		if(StringUtils.isNotBlank(beginDay) && StringUtils.isNotBlank(endDay) && Integer.valueOf(beginDay).intValue() < Integer.valueOf(endDay).intValue()){
//			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
//			DateTime beginDate = DateTime.parse(beginDay,format);
//			DateTime endDate = DateTime.parse(endDay,format);
//			while(beginDate.compareTo(endDate)<=0){
//				String insertDate = endDate.toString(format);
//				CqsscDay day = new CqsscDay();
//				day.setDAY(insertDate);
//				dayMapper.insert(day);
//				endDate = endDate.minusDays(1);
//			}
//		}
//	}


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
