package com.zrf.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.zrf.stock.entity.TjsscData;
import com.zrf.stock.entity.XjsscData;
import com.zrf.stock.service.CqsscServiceI;
import com.zrf.stock.service.TjsscServiceI;
import com.zrf.stock.service.XjsscServiceI;


@SuppressWarnings("deprecation")
@Component 
public class HttpClientUtil {
	
	@Resource
	private CqsscServiceI service;
	@Resource
	private TjsscServiceI tjservice;
	@Resource
	private XjsscServiceI xjservice; 
	
	public static String SSQ_TYPE_CQSSC = "cqssc";		//重庆时时彩
	public static String SSQ_TYPE_TJSSC = "tjssc";		//天津时时彩
	public static String SSQ_TYPE_XJSSC = "xjssc";		//新疆时时彩
	
	//时时彩当前开奖接口和日期开奖接口
	public static String SSC_URL = "http://a.apiplus.net/newly.do?token=2a2d75c8c792176f&format=json";	//&code=cqssc
	public static String SSC_URL_DAY = "http://a.apiplus.net/daily.do?token=2a2d75c8c792176f&format=json";	//&code=cqssc
	
	//插入开奖号码的开始与结束时时
	public static String beginDay = "2017-01-01";
	public static String endDay = "2017-07-24";
	
	
	
	@Scheduled(cron = "0 2/10 10-22 * * ?")  
	public void execute() throws ClientProtocolException, IOException{
		insertData("cqssc",SSC_URL);		//重庆时时彩
	}
	
	@Scheduled(cron = "0 2/5 0,1,22,23 * * ?")  
	public void execute2() throws ParseException, IOException{
		insertData("cqssc",SSC_URL);		//重庆时时彩
	}
	
	
	@Scheduled(cron = "0 3/10 09-22 * * ?")  
	public void executetj() throws ClientProtocolException, IOException{
		insertData("tjssc",SSC_URL);		//天津时时彩
	}
	
	
	@Scheduled(cron = "0 3/10 10-23,23,0,1 * * ?")  
	public void executexj() throws ClientProtocolException, IOException{
		insertData("xjssc",SSC_URL);		//新疆时时彩
	}
	
	
	@Scheduled(cron = "0 25 13 * * ?")   
	public void executeDays() throws ParseException, IOException, InterruptedException{
		try{
			executeDayData("cqssc",HttpClientUtil.beginDay,HttpClientUtil.endDay);		//重庆时时彩
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//@Scheduled(cron = "0 45 10 * * ?")   
	public void executetjDays() throws ParseException, IOException, InterruptedException{
		executeDayData("tjssc",HttpClientUtil.beginDay,HttpClientUtil.endDay);		//天津时时彩
	}
	
	//@Scheduled(cron = "0 45 10 * * ?")   
	public void executexjDays() throws ParseException, IOException, InterruptedException{
		executeDayData("xjssc",HttpClientUtil.beginDay,HttpClientUtil.endDay);		//新疆时时彩
	}
	
	
	@SuppressWarnings({ "resource" })
	public void insertData(String sscType,String url) throws ParseException, IOException{
		String realUrl = url+"&code="+sscType;
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(realUrl);
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
            if(SSQ_TYPE_CQSSC.equals(sscType)){		//重庆时时彩
            	insertCqsscCell(dataArray);
            }else if(SSQ_TYPE_TJSSC.equals(sscType)){	//天津时时彩 
            	insertTjsscCell(dataArray);
            }else if(SSQ_TYPE_XJSSC.equals(sscType)){	//新疆时时彩
            	insertXjsscCell(dataArray);
            }
            
        } else {
            System.out.println("未得到数据！");
        }
	}


	/**
	 * 插入重庆时时彩数据 
	 * @param dataArray
	 */
	private void insertCqsscCell(JsonArray dataArray) {
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
		    	fillCqsscOtherColumn(cell);
		    	service.save(cell);
			}else{
				System.out.println(object.get("expect").getAsString()+":"+object.get("opencode").getAsString()+";");
			}
		}
	}
	
	
	/**
	 * 插入新疆时时彩数据 
	 * @param dataArray
	 */
	private void insertXjsscCell(JsonArray dataArray) {
		for(int i=0;i<dataArray.size();i++){
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&"+object.get("expect").getAsString());
			XjsscData temp = xjservice.selectByPrimaryKey(object.get("expect").getAsString());
			if(temp==null){
				XjsscData cell = new XjsscData();
		    	cell.setID(object.get("expect").getAsString());
		    	cell.setDAY(object.get("expect").getAsString());
		    	String opencode = object.get("opencode").getAsString();
		    	cell.setNum(opencode.replace(",", ""));
		    	cell.setNumW(Integer.valueOf(opencode.split(",")[0]));
		    	cell.setNumQ(Integer.valueOf(opencode.split(",")[1]));
		    	cell.setNumB(Integer.valueOf(opencode.split(",")[2]));
		    	cell.setNumS(Integer.valueOf(opencode.split(",")[3]));
		    	cell.setNumG(Integer.valueOf(opencode.split(",")[4]));
		    	fillXjsscOtherColumn(cell);
		    	xjservice.save(cell);
			}else{
				System.out.println(object.get("expect").getAsString()+":"+object.get("opencode").getAsString()+";");
			}
		}
	}
	
	
	/**
	 * 插入天津时时彩数据 
	 * @param dataArray
	 */
	private void insertTjsscCell(JsonArray dataArray) {
		for(int i=0;i<dataArray.size();i++){
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&"+object.get("expect").getAsString());
			TjsscData temp = tjservice.selectByPrimaryKey(object.get("expect").getAsString());
			if(temp==null){
				TjsscData cell = new TjsscData();
		    	cell.setID(object.get("expect").getAsString());
		    	cell.setDAY(object.get("expect").getAsString());
		    	String opencode = object.get("opencode").getAsString();
		    	cell.setNum(opencode.replace(",", ""));
		    	cell.setNumW(Integer.valueOf(opencode.split(",")[0]));
		    	cell.setNumQ(Integer.valueOf(opencode.split(",")[1]));
		    	cell.setNumB(Integer.valueOf(opencode.split(",")[2]));
		    	cell.setNumS(Integer.valueOf(opencode.split(",")[3]));
		    	cell.setNumG(Integer.valueOf(opencode.split(",")[4]));
		    	fillTjsscOtherColumn(cell);
		    	tjservice.save(cell);
			}else{
				System.out.println(object.get("expect").getAsString()+":"+object.get("opencode").getAsString()+";");
			}
		}
	}
	
	
	/**
	 * 填充10路三星开奖号码类型(重庆时时彩)
	 * @param cell
	 * @return
	 */
	public CqsscData fillCqsscOtherColumn(CqsscData cell){
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();
		
		cell.setIsWqb(getType(dataW.intValue(),dataQ.intValue(),dataB.intValue()));   //前三
		cell.setIsQbs(getType(dataQ.intValue(),dataB.intValue(),dataS.intValue()));   //中三
		cell.setIsBsg(getType(dataB.intValue(),dataS.intValue(),dataG.intValue()));   //后三
		cell.setIsWqs(getType(dataW.intValue(),dataQ.intValue(),dataS.intValue()));   //万千十
		cell.setIsWqg(getType(dataW.intValue(),dataQ.intValue(),dataG.intValue()));   //万千个
		cell.setIsWbs(getType(dataW.intValue(),dataB.intValue(),dataS.intValue()));   //万百十
		cell.setIsWbg(getType(dataW.intValue(),dataB.intValue(),dataG.intValue()));   //万百个
		cell.setIsWsg(getType(dataW.intValue(),dataS.intValue(),dataG.intValue()));   //万十个
		cell.setIsQbg(getType(dataQ.intValue(),dataB.intValue(),dataG.intValue()));   //千百个
		cell.setIsQsg(getType(dataQ.intValue(),dataS.intValue(),dataG.intValue()));   //千十个
		cell.setBsgType(is012(dataB.intValue(),dataS.intValue(),dataG.intValue()));   //后三012路
		int []wxdata = {dataW.intValue(),dataQ.intValue(),dataB.intValue(),dataS.intValue(),dataG.intValue()};	
		cell.setWxType(getWxType(wxdata));		//五星类型
		return cell;
	}
	
	
	/**
	 * 填充10路三星开奖号码类型(天津时时彩)
	 * @param cell
	 * @return
	 */
	public TjsscData fillTjsscOtherColumn(TjsscData cell){
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();
		
		cell.setIsWqb(getType(dataW.intValue(),dataQ.intValue(),dataB.intValue()));   //前三
		cell.setIsQbs(getType(dataQ.intValue(),dataB.intValue(),dataS.intValue()));   //中三
		cell.setIsBsg(getType(dataB.intValue(),dataS.intValue(),dataG.intValue()));   //后三
		cell.setIsWqs(getType(dataW.intValue(),dataQ.intValue(),dataS.intValue()));   //万千十
		cell.setIsWqg(getType(dataW.intValue(),dataQ.intValue(),dataG.intValue()));   //万千个
		cell.setIsWbs(getType(dataW.intValue(),dataB.intValue(),dataS.intValue()));   //万百十
		cell.setIsWbg(getType(dataW.intValue(),dataB.intValue(),dataG.intValue()));   //万百个
		cell.setIsWsg(getType(dataW.intValue(),dataS.intValue(),dataG.intValue()));   //万十个
		cell.setIsQbg(getType(dataQ.intValue(),dataB.intValue(),dataG.intValue()));   //千百个
		cell.setIsQsg(getType(dataQ.intValue(),dataS.intValue(),dataG.intValue()));   //千十个
		
		return cell;
	}
	
	
	/**
	 * 填充10路三星开奖号码类型(新疆时时彩)
	 * @param cell
	 * @return
	 */
	public XjsscData fillXjsscOtherColumn(XjsscData cell){
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();
		
		cell.setIsWqb(getType(dataW.intValue(),dataQ.intValue(),dataB.intValue()));   //前三
		cell.setIsQbs(getType(dataQ.intValue(),dataB.intValue(),dataS.intValue()));   //中三
		cell.setIsBsg(getType(dataB.intValue(),dataS.intValue(),dataG.intValue()));   //后三
		cell.setIsWqs(getType(dataW.intValue(),dataQ.intValue(),dataS.intValue()));   //万千十
		cell.setIsWqg(getType(dataW.intValue(),dataQ.intValue(),dataG.intValue()));   //万千个
		cell.setIsWbs(getType(dataW.intValue(),dataB.intValue(),dataS.intValue()));   //万百十
		cell.setIsWbg(getType(dataW.intValue(),dataB.intValue(),dataG.intValue()));   //万百个
		cell.setIsWsg(getType(dataW.intValue(),dataS.intValue(),dataG.intValue()));   //万十个
		cell.setIsQbg(getType(dataQ.intValue(),dataB.intValue(),dataG.intValue()));   //千百个
		cell.setIsQsg(getType(dataQ.intValue(),dataS.intValue(),dataG.intValue()));   //千十个
		
		return cell;
	}
	
	
	/**
	 * 获得三位开奖号码的类型  1：组三  2：豹子  0:组六	
	 * @param m1
	 * @param m2
	 * @param m3
	 * @return
	 */
	public static Integer getType(int m1,int m2,int m3){
		Integer rtnInt = Integer.valueOf("0");		//组六
		if(m1==m2 && m2==m3){
			return Integer.valueOf("2");		//豹子
		}else if(m1==m2 || m1==m3 || m2==m3){
			return Integer.valueOf("1");		//组三
		}
		return  rtnInt;
	}
	
	/**
	 * 是否是012路	
	 * @param m1
	 * @param m2
	 * @param m3
	 * @return
	 */
	public static Integer is012(int m1,int m2,int m3){
		int type = getType(m1,m2,m3).intValue();
		Integer rtnInt = Integer.valueOf("0");		//非012路
		if(type==0 && ((m1%3!=m2%3 && m1%3!=m3%3 && m2%3!=m3%3) && !((m1<5 && m2<5 && m3<5)||(m1>4 && m2>4 && m3>4)))){
			return Integer.valueOf("1");
		}
		return  rtnInt;
	}
	
	/**
	 * 得到五星类型 0：其它	1：组选30；2：组选20；3：组选10 4：組選5
	 * @param m1
	 * @param m2
	 * @param m3
	 * @param m4
	 * @param m5
	 * @return
	 */
	public static Integer getWxType(int[] m){
		Integer rtnInt = Integer.valueOf("0");		
		Map<String,String> map = new HashMap();
		for(int i=0;i<m.length;i++){
			if(map.containsKey(m[i]+"")){
				String newValue = String.valueOf(Integer.valueOf(map.get(m[i]+"")).intValue()+1);
				map.put(m[i]+"", newValue);
			}else{
				map.put(m[i]+"", "1");
			}
		}
		int length = map.size();
		if(length==2){
			rtnInt = Integer.valueOf("3");
			for(Entry entry:map.entrySet()){
				if("4".equals(entry.getValue().toString())){
					rtnInt = Integer.valueOf("4"); 
					break;
				}
			}
		}else if(length==3){
			rtnInt = Integer.valueOf("1");
			for(Entry entry:map.entrySet()){
				if("3".equals(entry.getValue().toString())){
					rtnInt = Integer.valueOf("2"); 
					break;
				}
			}
		}
		return  rtnInt;
	}
	
	
	

	
	public void executeDayData(String sscType,String beginDay,String endDay)throws ParseException, IOException, InterruptedException{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");  
		DateTime beginTime = DateTime.parse(beginDay, formatter);
		DateTime endTime = DateTime.parse(endDay, formatter);
		String endNextDay = endTime.plusDays(1).toString(formatter);
		String nextDay = beginTime.plusDays(1).toString(formatter);
		do{
			System.out.println("##########execute day:"+beginDay+" bgein");
			String fullUrl = HttpClientUtil.SSC_URL_DAY+"&date="+beginDay+"&code="+sscType;
			insertData(sscType,fullUrl);
			System.out.println("##########execute day:"+beginDay+" end");
			beginDay = nextDay;
			beginTime = beginTime.plusDays(1);
			nextDay = beginTime.plusDays(1).toString(formatter);
			Thread.sleep(10000);
		}while(!beginDay.equals(endNextDay));
	}
	
	
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		int[] a = {1,2,2,2,1};
		System.out.println(is012(8,7,6));
	}

}
