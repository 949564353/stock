package com.zrf.stock.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zrf.stock.dao.XjsscDataMapper;
import com.zrf.stock.util.HttpClientUtil;
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
import com.zrf.stock.entity.XjsscData;
import com.zrf.stock.service.CqsscServiceI;
import com.zrf.stock.service.XjsscServiceI;

@Controller
@RequestMapping(value="/xjssc")
public class XjsscController {
	
	@Resource
	private XjsscServiceI service;
	@Resource
	private XjsscDataMapper mapper;
	
	@RequestMapping(value="/getCurrentDay")
	@ResponseBody
	private String getCurrentNum(HttpServletRequest request){
		String selectDay = request.getParameter("selectDay");
		if(!StringUtils.isNotBlank(selectDay)){
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
	        //时间解析
	        selectDay = DateTime.now().minusHours(10).minusMinutes(11).toString(format);
		}
        //String currentDay = "20170531";
        List<XjsscData> list =  service.getCurrentNum(selectDay);
        JsonArray array = new JsonArray();
        String lastNo = "";
        for(int i=0;i<list.size();i++){
        	XjsscData data = list.get(i);
    		JsonObject json = new JsonObject();
    		json.addProperty("day", data.getDAY().substring(0,8));
    		json.addProperty("no", data.getDAY().substring(8, 11));
    		json.addProperty("num", data.getNum());
    		Integer bsg = data.getIsBsg();
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
        for(int j=lastNum+1;j<=96;j++){
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
	
	
	
	@RequestMapping(value="/getBzList")
	@ResponseBody
	private String getBzList(HttpServletRequest request){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");  
        //时间解析    
        String currentDay = DateTime.now().minusHours(3).toString(format);
        List<XjsscData> list =  service.getBzList(currentDay);
        JsonArray array = new JsonArray();
        for(int i=0;i<list.size();i++){
        	XjsscData data = list.get(i);
    		JsonObject json = new JsonObject();
    		if(Integer.valueOf(data.getDAY()).intValue()<=Integer.valueOf(currentDay).intValue()){
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
        		array.add(json);
    		}
        }
        return array.toString();
	}


	@RequestMapping(value="/setWxType")
	@ResponseBody
	private String setWxType(HttpServletRequest request){

		List<XjsscData> list = service.getAllData();
		for(XjsscData data:list){
			int[] wxdata = { data.getNumW().intValue(), data.getNumQ().intValue(), data.getNumB().intValue(), data.getNumS().intValue(), data.getNumG().intValue() };
			Integer wxType = HttpClientUtil.getWxType(wxdata);
			data.setWxType(wxType);
			data.setBsgType(HttpClientUtil.is012(data.getNumB().intValue(), data.getNumS().intValue(), data.getNumG().intValue()));
			mapper.updateByPrimaryKey(data);
		}
		return "1";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
