package com.zrf.stock.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.entity.Gd11X5Data;
import com.zrf.stock.entity.Sd11X5Data;
import com.zrf.stock.entity.Sh11X5Data;
import com.zrf.stock.entity.TjsscData;
import com.zrf.stock.entity.XjsscData;
import com.zrf.stock.service.CqsscServiceI;
import com.zrf.stock.service.Gd11X5ServiceI;
import com.zrf.stock.service.Sd11X5ServiceI;
import com.zrf.stock.service.Sh11X5ServiceI;
import com.zrf.stock.service.TjsscServiceI;
import com.zrf.stock.service.XjsscServiceI;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil
{

	@Resource
	private CqsscServiceI service;

	@Resource
	private TjsscServiceI tjservice;

	@Resource
	private Gd11X5ServiceI gd11X5Service;

	@Resource
	private Sh11X5ServiceI sh11X5Service;

	@Resource
	private Sd11X5ServiceI sd11X5Service;

	@Resource
	private XjsscServiceI xjservice;
	public static String SSQ_TYPE_CQSSC = "cqssc";
	public static String SSQ_TYPE_TJSSC = "tjssc";
	public static String SSQ_TYPE_XJSSC = "xjssc";


	public static String GD11X5_TYPE_XJSSC = "gd11x5";
	public static String SH11X5_TYPE_XJSSC = "sh11x5";
	public static String SD11X5_TYPE_XJSSC = "sd11x5";

	public static String SSC_URL = "http://a.apiplus.net/newly.do?token=2a2d75c8c792176f&format=json";
	public static String SSC_URL_DAY = "http://a.apiplus.net/daily.do?token=2a2d75c8c792176f&format=json";

	public static String beginDay = "2018-12-01";
	public static String endDay = "2018-12-11";

	@Scheduled(cron="0 2/10 10-22 * * ?")
	public void execute()
			throws ClientProtocolException, IOException
	{
		insertData("cqssc", SSC_URL);
	}

	@Scheduled(cron="0 2/5 0,1,22,23 * * ?")
	public void execute2() throws ParseException, IOException {
		insertData("cqssc", SSC_URL);
	}

	@Scheduled(cron="0 3/10 09-22 * * ?")
	public void executetj() throws ClientProtocolException, IOException
	{
		insertData("tjssc", SSC_URL);
	}

	@Scheduled(cron="0 3/10 10-23,23,0,1 * * ?")
	public void executexj() throws ClientProtocolException, IOException
	{
		insertData("xjssc", SSC_URL);
	}

	@Scheduled(cron="0 2/10 09-23 * * ?")
	public void executegd11x5() throws ClientProtocolException, IOException
	{
		insertData("gd11x5", SSC_URL);
	}

	@Scheduled(cron="0 8/10 09-23 * * ?")
	public void executesd11x5() throws ClientProtocolException, IOException
	{
		insertData("sd11x5", SSC_URL);
	}

	@Scheduled(cron="0 2/10 09-23 * * ?")
	public void executesh11x5() throws ClientProtocolException, IOException
	{
		insertData("sh11x5", SSC_URL);
	}

	@Scheduled(cron="0 01 22 * * ?")
	public void executeDays() throws ParseException, IOException, InterruptedException
	{
		try {
			//executeDayData("xjssc", beginDay, endDay);
			execute11X5DayData("sh11x5", beginDay, endDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executetjDays() throws ParseException, IOException, InterruptedException
	{
		executeDayData("tjssc", beginDay, endDay);
	}


	public void executexjDays() throws ParseException, IOException, InterruptedException
	{
		executeDayData("xjssc", beginDay, endDay);
	}

	public void insert11X5Data(String sscType, String url)
			throws ParseException, IOException
	{
		String realUrl = url + "&code=" + sscType;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(realUrl);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == 200)
		{
			String strResult = EntityUtils.toString(response.getEntity());
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(strResult);
			JsonObject jsonObj = element.getAsJsonObject();
			String data = jsonObj.get("data").toString();
			JsonElement dataElement = jsonParser.parse(data);
			JsonArray dataArray = dataElement.getAsJsonArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(new Date());
			System.out.println("===========================current=" + now);
			if (GD11X5_TYPE_XJSSC.equals(sscType))		//广东11选5
				insertGd11X5Cell(dataArray);
			else if(SD11X5_TYPE_XJSSC.equals(sscType))
				insertSd11X5Cell(dataArray);
			else if(SH11X5_TYPE_XJSSC.equals(sscType))
				insertSh11X5Cell(dataArray);
		}
		else
		{
			System.out.println("未得到数据！");
		}
	}

	public void insertData(String sscType, String url)
			throws ParseException, IOException
	{
		String realUrl = url + "&code=" + sscType;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(realUrl);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == 200)
		{
			String strResult = EntityUtils.toString(response.getEntity());
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(strResult);
			JsonObject jsonObj = element.getAsJsonObject();
			String data = jsonObj.get("data").toString();
			JsonElement dataElement = jsonParser.parse(data);
			JsonArray dataArray = dataElement.getAsJsonArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(new Date());
			System.out.println("===========================current=" + now);
			if (SSQ_TYPE_CQSSC.equals(sscType))
				insertCqsscCell(dataArray);
			else if (SSQ_TYPE_TJSSC.equals(sscType))
				insertTjsscCell(dataArray);
			else if (SSQ_TYPE_XJSSC.equals(sscType))
				insertXjsscCell(dataArray);
			else if(GD11X5_TYPE_XJSSC.equals(sscType))
				insertGd11X5Cell(dataArray);
			else if(SD11X5_TYPE_XJSSC.equals(sscType))
				insertSd11X5Cell(dataArray);
			else if(SH11X5_TYPE_XJSSC.equals(sscType))
				insertSh11X5Cell(dataArray);
		}
		else
		{
			System.out.println("未得到数据！");
		}
	}

	/**
	 * 插入广东11选5
	 * @param dataArray
	 */
	private void insertGd11X5Cell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			Gd11X5Data temp = this.gd11X5Service.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
				Gd11X5Data cell = new Gd11X5Data();
				cell.setID(object.get("expect").getAsString());
				cell.setDAY(object.get("expect").getAsString());
				String opencode = object.get("opencode").getAsString();
				cell.setNum(opencode.replace(",", ""));
				String numW = opencode.split(",")[0];
				String numQ = opencode.split(",")[1];
				String numB = opencode.split(",")[2];
				String numS = opencode.split(",")[3];
				String numG = opencode.split(",")[4];
				cell.setNumW(numW);
				cell.setNumQ(numQ);
				cell.setNumB(numB);
				cell.setNumS(numS);
				cell.setNumG(numG);

				//设置奇偶数
				int oddNum = Integer.valueOf(numW)%2 + Integer.valueOf(numQ)%2 + Integer.valueOf(numB)%2 + Integer.valueOf(numS)%2 + Integer.valueOf(numG)%2;
				cell.setOddNum(oddNum);
				cell.setEvenNum(5-oddNum);

				//设置中位数
				List<Integer> numList = new ArrayList<>();
				numList.add(Integer.valueOf(numW));
				numList.add(Integer.valueOf(numQ));
				numList.add(Integer.valueOf(numB));
				numList.add(Integer.valueOf(numS));
				numList.add(Integer.valueOf(numG));
				Collections.sort(numList);
				cell.setNumMiddle(numList.get(2).toString());

				this.gd11X5Service.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	/**
	 * 插入山东11选5
	 * @param dataArray
	 */
	private void insertSd11X5Cell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			Sd11X5Data temp = this.sd11X5Service.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
				Sd11X5Data cell = new Sd11X5Data();
				cell.setID(object.get("expect").getAsString());
				cell.setDAY(object.get("expect").getAsString());
				String opencode = object.get("opencode").getAsString();
				cell.setNum(opencode.replace(",", ""));
				String numW = opencode.split(",")[0];
				String numQ = opencode.split(",")[1];
				String numB = opencode.split(",")[2];
				String numS = opencode.split(",")[3];
				String numG = opencode.split(",")[4];
				cell.setNumW(numW);
				cell.setNumQ(numQ);
				cell.setNumB(numB);
				cell.setNumS(numS);
				cell.setNumG(numG);

				//设置奇偶数
				int oddNum = Integer.valueOf(numW)%2 + Integer.valueOf(numQ)%2 + Integer.valueOf(numB)%2 + Integer.valueOf(numS)%2 + Integer.valueOf(numG)%2;
				cell.setOddNum(oddNum);
				cell.setEvenNum(5-oddNum);

				//设置中位数
				List<Integer> numList = new ArrayList<>();
				numList.add(Integer.valueOf(numW));
				numList.add(Integer.valueOf(numQ));
				numList.add(Integer.valueOf(numB));
				numList.add(Integer.valueOf(numS));
				numList.add(Integer.valueOf(numG));
				Collections.sort(numList);
				cell.setNumMiddle(numList.get(2).toString());

				this.sd11X5Service.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	/**
	 * 插入上海11选5
	 * @param dataArray
	 */
	private void insertSh11X5Cell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			Sh11X5Data temp = this.sh11X5Service.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
				Sh11X5Data cell = new Sh11X5Data();
				cell.setID(object.get("expect").getAsString());
				cell.setDAY(object.get("expect").getAsString());
				String opencode = object.get("opencode").getAsString();
				cell.setNum(opencode.replace(",", ""));
				String numW = opencode.split(",")[0];
				String numQ = opencode.split(",")[1];
				String numB = opencode.split(",")[2];
				String numS = opencode.split(",")[3];
				String numG = opencode.split(",")[4];
				cell.setNumW(numW);
				cell.setNumQ(numQ);
				cell.setNumB(numB);
				cell.setNumS(numS);
				cell.setNumG(numG);

				//设置奇偶数
				int oddNum = Integer.valueOf(numW)%2 + Integer.valueOf(numQ)%2 + Integer.valueOf(numB)%2 + Integer.valueOf(numS)%2 + Integer.valueOf(numG)%2;
				cell.setOddNum(oddNum);
				cell.setEvenNum(5-oddNum);

				//设置中位数
				List<Integer> numList = new ArrayList<>();
				numList.add(Integer.valueOf(numW));
				numList.add(Integer.valueOf(numQ));
				numList.add(Integer.valueOf(numB));
				numList.add(Integer.valueOf(numS));
				numList.add(Integer.valueOf(numG));
				Collections.sort(numList);
				cell.setNumMiddle(numList.get(2).toString());

				this.sh11X5Service.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}


	private void insertCqsscCell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			CqsscData temp = this.service.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
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
				this.service.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	private void insertXjsscCell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			XjsscData temp = this.xjservice.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
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
				this.xjservice.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	private void insertGd11x5Cell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			Gd11X5Data temp = this.gd11X5Service.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
				Gd11X5Data cell = new Gd11X5Data();
				cell.setID(object.get("expect").getAsString());
				cell.setDAY(object.get("expect").getAsString());
				String opencode = object.get("opencode").getAsString();
				cell.setNum(opencode.replace(",", ""));
				cell.setNumW(opencode.split(",")[0]);
				cell.setNumQ(opencode.split(",")[1]);
				cell.setNumB(opencode.split(",")[2]);
				cell.setNumS(opencode.split(",")[3]);
				cell.setNumG(opencode.split(",")[4]);
				this.gd11X5Service.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	private void insertTjsscCell(JsonArray dataArray)
	{
		for (int i = 0; i < dataArray.size(); i++) {
			JsonObject object = dataArray.get(i).getAsJsonObject();
			System.out.println("&&&&&&&&&&&&&" + object.get("expect").getAsString());
			TjsscData temp = this.tjservice.selectByPrimaryKey(object.get("expect").getAsString());
			if (temp == null) {
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
				this.tjservice.save(cell);
			} else {
				System.out.println(object.get("expect").getAsString() + ":" + object.get("opencode").getAsString() + ";");
			}
		}
	}

	public CqsscData fillCqsscOtherColumn(CqsscData cell)
	{
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();

		cell.setIsWqb(getType(dataW.intValue(), dataQ.intValue(), dataB.intValue()));
		cell.setIsQbs(getType(dataQ.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsBsg(getType(dataB.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsWqs(getType(dataW.intValue(), dataQ.intValue(), dataS.intValue()));
		cell.setIsWqg(getType(dataW.intValue(), dataQ.intValue(), dataG.intValue()));
		cell.setIsWbs(getType(dataW.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsWbg(getType(dataW.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsWsg(getType(dataW.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsQbg(getType(dataQ.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsQsg(getType(dataQ.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setBsgType(is012(dataB.intValue(), dataS.intValue(), dataG.intValue()));
		int[] wxdata = { dataW.intValue(), dataQ.intValue(), dataB.intValue(), dataS.intValue(), dataG.intValue() };
		cell.setWxType(getWxType(wxdata));
		return cell;
	}

	public TjsscData fillTjsscOtherColumn(TjsscData cell)
	{
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();

		cell.setIsWqb(getType(dataW.intValue(), dataQ.intValue(), dataB.intValue()));
		cell.setIsQbs(getType(dataQ.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsBsg(getType(dataB.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsWqs(getType(dataW.intValue(), dataQ.intValue(), dataS.intValue()));
		cell.setIsWqg(getType(dataW.intValue(), dataQ.intValue(), dataG.intValue()));
		cell.setIsWbs(getType(dataW.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsWbg(getType(dataW.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsWsg(getType(dataW.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsQbg(getType(dataQ.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsQsg(getType(dataQ.intValue(), dataS.intValue(), dataG.intValue()));

		return cell;
	}

	public XjsscData fillXjsscOtherColumn(XjsscData cell)
	{
		Integer dataW = cell.getNumW();
		Integer dataQ = cell.getNumQ();
		Integer dataB = cell.getNumB();
		Integer dataS = cell.getNumS();
		Integer dataG = cell.getNumG();

		cell.setIsWqb(getType(dataW.intValue(), dataQ.intValue(), dataB.intValue()));
		cell.setIsQbs(getType(dataQ.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsBsg(getType(dataB.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsWqs(getType(dataW.intValue(), dataQ.intValue(), dataS.intValue()));
		cell.setIsWqg(getType(dataW.intValue(), dataQ.intValue(), dataG.intValue()));
		cell.setIsWbs(getType(dataW.intValue(), dataB.intValue(), dataS.intValue()));
		cell.setIsWbg(getType(dataW.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsWsg(getType(dataW.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setIsQbg(getType(dataQ.intValue(), dataB.intValue(), dataG.intValue()));
		cell.setIsQsg(getType(dataQ.intValue(), dataS.intValue(), dataG.intValue()));
		cell.setBsgType(is012(dataB.intValue(), dataS.intValue(), dataG.intValue()));
		int[] wxdata = { dataW.intValue(), dataQ.intValue(), dataB.intValue(), dataS.intValue(), dataG.intValue() };
		cell.setWxType(getWxType(wxdata));
		return cell;
	}

	public static Integer getType(int m1, int m2, int m3)
	{
		Integer rtnInt = Integer.valueOf("0");
		if ((m1 == m2) && (m2 == m3))
			return Integer.valueOf("2");
		if ((m1 == m2) || (m1 == m3) || (m2 == m3)) {
			return Integer.valueOf("1");
		}
		return rtnInt;
	}

	public static Integer is012(int m1, int m2, int m3)
	{
		int type = getType(m1, m2, m3).intValue();
		Integer rtnInt = Integer.valueOf("0");
		if ((type == 0) && (((m1 % 3 != m2 % 3) && (m1 % 3 != m3 % 3) && (m2 % 3 != m3 % 3) && (m1 < 5) && (m2 < 5) && (m3 < 5)) || ((m1 > 4) && (m2 > 4) && (m3 > 4)))) {
			return Integer.valueOf("1");
		}
		return rtnInt;
	}

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
		}else if(length==4){
			rtnInt = Integer.valueOf("7");
		}else if(length==5){
			rtnInt = Integer.valueOf("6");
		}
		return  rtnInt;
	}

	public void executeDayData(String sscType, String beginDay, String endDay)
			throws ParseException, IOException, InterruptedException
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime beginTime = DateTime.parse(beginDay, formatter);
		DateTime endTime = DateTime.parse(endDay, formatter);
		String endNextDay = endTime.plusDays(1).toString(formatter);
		String nextDay = beginTime.plusDays(1).toString(formatter);
		do {
			System.out.println("##########execute day:" + beginDay + " bgein");
			String fullUrl = SSC_URL_DAY + "&date=" + beginDay + "&code=" + sscType;
			insertData(sscType, fullUrl);
			System.out.println("##########execute day:" + beginDay + " end");
			beginDay = nextDay;
			beginTime = beginTime.plusDays(1);
			nextDay = beginTime.plusDays(1).toString(formatter);
			Thread.sleep(10000L);
		}while (!beginDay.equals(endNextDay));
	}

	public void execute11X5DayData(String sscType, String beginDay, String endDay)
			throws ParseException, IOException, InterruptedException
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime beginTime = DateTime.parse(beginDay, formatter);
		DateTime endTime = DateTime.parse(endDay, formatter);
		String endNextDay = endTime.plusDays(1).toString(formatter);
		String nextDay = beginTime.plusDays(1).toString(formatter);
		do {
			System.out.println("##########execute day:" + beginDay + " bgein");
			String fullUrl = SSC_URL_DAY + "&date=" + beginDay + "&code=" + sscType;
			insert11X5Data(sscType, fullUrl);
			System.out.println("##########execute day:" + beginDay + " end");
			beginDay = nextDay;
			beginTime = beginTime.plusDays(1);
			nextDay = beginTime.plusDays(1).toString(formatter);
			Thread.sleep(10000L);
		}while (!beginDay.equals(endNextDay));
	}

	public static void main(String[] args)
			throws ClientProtocolException, IOException
	{
//		int[] a = { 9, 5, 6, 2, 4 };
//		//System.out.println(getWxType(a));
//		int type = 0;
//		int m1 = 6;
//		int m2=2;
//		int m3=4;
//		Integer rtnInt = Integer.valueOf("0");
//		if ((type == 0) && (((m1 % 3 != m2 % 3) && (m1 % 3 != m3 % 3) && (m2 % 3 != m3 % 3) && (m1 < 5) && (m2 < 5) && (m3 < 5)) || ((m1 > 4) && (m2 > 4) && (m3 > 4)))) {
//			System.out.println("1111");
//		}

				int[] a = { 8, 8, 1, 1, 5 };
		System.out.println(getType(3,3,3));

//		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
//		//时间解析
//		String currentDay = DateTime.now().toString(format);
//		System.out.println(currentDay);
	}
}