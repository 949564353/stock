package com.zrf.stock.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zrf.stock.entity.CqsscData;
import com.zrf.stock.service.CqsscServiceI;

@Resource
@Controller
@ServerEndpoint("/websocketTest")
public class WebSocketController {
	
	@Resource
	private CqsscServiceI service;

//	@OnMessage
//	public void onMessage(String message, Session session) throws IOException, InterruptedException {
//		Calendar now = Calendar.getInstance();
//		int minute = now.get(now.MINUTE);
//		if (minute % 10 < 10) {
//			session.getBasicRemote().sendText(now.get(now.HOUR) + ":" + now.get(now.MINUTE));
//			// Thread.sleep(60000);
//		}
//	}

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 * @throws InterruptedException 
	 */
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("来自客户端的消息:" + message);
		System.out.println(message+"====");
		
		String openData = getCqsscData(message);
		for (WebSocketController item : webSocketSet) {
			try {
				item.sendMessage(openData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
//		while(1>0){
//			// 群发消息
//			for (WebSocketController item : webSocketSet) {
//				try {
//					Calendar now = Calendar.getInstance();
//					int minute = now.get(now.MINUTE);
//					if (minute % 10 < 10) {
//						now =Calendar.getInstance();
//						minute = now.get(now.MINUTE);
//						System.out.println("!!!!!!!!");
//						item.sendMessage(now.get(now.HOUR) + ":" + now.get(now.MINUTE));
//						Thread.sleep(6000);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//					continue;
//				}
//			}
//		}
//		
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		System.out.println(message+"----");
		this.session.getBasicRemote().sendText(message);
		// this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketController.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketController.onlineCount--;
	}
	
	
	
	public String getCqsscData(String selectDay){
		WebApplicationContext wac =ContextLoader.getCurrentWebApplicationContext();
		//wac.getBean("cqsscServiceI");

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
    				json.addProperty("bsg", "1");        		
    			}else if(bsg.intValue()==2){
    				json.addProperty("bsg", "2");
    			}else{
    				json.addProperty("bsg", "0");
    			}
    		}else{
    			json.addProperty("bsg", "0");
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
}
