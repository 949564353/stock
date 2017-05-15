package com.zrf.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.service.CqsscServiceI;


@Component
public class HttpClientUtil {
	
	@Resource
	private CqsscServiceI service;
	
	//http://t.apiplus.cn/newly.do?token=bf56ef2ad222836615d84281d5f9bd5f&code=cqssc&format=json
	public static String CQSSC_URL = "http://a.apiplus.net/newly.do?token=2a2d75c8c792176f&code=cqssc&format=json";
	public static String CQSSC_URL_DAY = "http://a.apiplus.net/daily.do?token=2a2d75c8c792176f&code=cqssc&format=json";	//&date=2017-05-14
	
	public static String beginDay = "2017-04-01";
	public static String endDay = "2017-05-01";
	
	
	//@Scheduled(cron = "0 0/5 * * * ?")  
	public void insertData(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity());
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(strResult);
            JsonObject jsonObj = element.getAsJsonObject();
            String data = jsonObj.get("data").toString();
            JsonElement dataElement = jsonParser.parse(data);
            JsonArray dataArray = dataElement.getAsJsonArray();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            String now = sdf.format(new Date()); 
            System.out.println("===========================current="+now);
            //System.out.print("======");
            for(int i=0;i<dataArray.size();i++){
            	JsonObject object = dataArray.get(i).getAsJsonObject();
            	System.out.println("&&&&&&&&&&&&&"+object.get("expect").getAsString());
            	CqsscData temp = service.selectByPrimaryKey(object.get("expect").getAsString());
            	if(temp==null){
            		CqsscData cell = new CqsscData();
                	cell.setID(object.get("expect").getAsString());
                	cell.setDAY(object.get("expect").getAsString());
                	String opencode = object.get("opencode").getAsString();
                	cell.setNum(opencode.replace(",", ""));
                	cell.setNumW(Integer.valueOf(opencode.split(",")[0]));
                	cell.setNumQ(Integer.valueOf(opencode.split(",")[1]));
                	cell.setNumB(Integer.valueOf(opencode.split(",")[2]));
                	cell.setNumS(Integer.valueOf(opencode.split(",")[3]));
                	cell.setNumG(Integer.valueOf(opencode.split(",")[4]));
                	service.save(cell);
            	}else{
            		System.out.println(object.get("expect").getAsString()+":"+object.get("opencode").getAsString()+";");
            	}
            	//System.out.print(object.get("expect").getAsString()+":"+object.get("opencode").getAsString()+""+object.get("opentime").getAsString()+";");
            }
            //System.out.println("=====");
        } else {
            System.out.println("未得到数据！");
        }
	}
	
	@Scheduled(cron = "0 2/10 10-22 * * ?")  
	public void execute() throws ClientProtocolException, IOException{
		insertData(CQSSC_URL);
	}
	
	@Scheduled(cron = "0 2/5 0,1,22,23 * * ?")  
	public void execute2() throws ParseException, IOException{
		insertData(CQSSC_URL);
	}
	
	@Scheduled(cron = "0 50 23 * * ?")   
	public void executeDays() throws ParseException, IOException, InterruptedException{
		executeDayData(HttpClientUtil.beginDay,HttpClientUtil.endDay);
	}
	
	public void executeDayData(String beginDay,String endDay)throws ParseException, IOException, InterruptedException{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");  
		DateTime beginTime = DateTime.parse(beginDay, formatter);
		DateTime endTime = DateTime.parse(endDay, formatter);
		String endNextDay = endTime.plusDays(1).toString(formatter);
		String nextDay = beginTime.plusDays(1).toString(formatter);
		do{
			System.out.println("##########execute day:"+beginDay+" bgein");
			String fullUrl = HttpClientUtil.CQSSC_URL_DAY+"&date="+beginDay;
			System.out.println("^^^^^^^"+fullUrl);
			insertData(fullUrl);
			System.out.println("##########execute day:"+beginDay+" end");
			beginDay = nextDay;
			beginTime = beginTime.plusDays(1);
			nextDay = beginTime.plusDays(1).toString(formatter);
			Thread.sleep(10000);
		}while(!beginDay.equals(endNextDay));
	}
	
	
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
	}

}
