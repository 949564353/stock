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
	
	public static String CQSSC_URL = "http://f.apiplus.cn/cqssc-10.json";
	
	public void inertData() throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(HttpClientUtil.CQSSC_URL);
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
		inertData();
	}
	
	@Scheduled(cron = "0 2/5 0,1,22,23 * * ?")  
	public void execute2() throws ParseException, IOException{
		inertData();
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
	}

}
