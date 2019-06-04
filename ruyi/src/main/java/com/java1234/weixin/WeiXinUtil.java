package com.java1234.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.java1234.entity.Access_token;
import com.java1234.http.HttpClient;

import net.sf.json.JSONObject;

public class WeiXinUtil {
	
	public static void main(String[] args) {
		
	}
	
	
	/**
	 * 
	 * 使用code 拿openid和access_token
	 * 
	 */
	public  static String getOpenidUrl(String code) {
		return "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+WeiXinConfig.appID+""
				+ "&secret="+WeiXinConfig.appsecret+""
				+ "&code="+code+""
				+ "&grant_type=authorization_code";
	}
	
	/**
	 * 使用openid 换取 用户资料
	 */
	public static String getWXUserInfoUrl(JSONObject accessToken_openid_obj) {
		
		return "https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token="+accessToken_openid_obj.getString("access_token").toString()+""
				+ "&openid="+accessToken_openid_obj.getString("openid").toString()+"";
	}
	
	
	
	
	/**
	 * 拿到Access_token
	 * @return
	 */
	public static Access_token getToken() {
		Gson gson = new Gson();
		Access_token access_token = null ; 
		String access_token_json = HttpClient.get(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WeiXinConfig.appID+"&secret="+WeiXinConfig.appsecret+"");
		access_token = gson.fromJson(access_token_json, Access_token.class);
		return access_token;
	}
	
	/**
	 * 将上面的参数 打包成
	  {
	    "touser":"OPENID",
	    "msgtype":"text",
	    "text":
	    { "content":"Hello World" }
	  }
	 * @param openid
	 * @param msgtype
	 * @param content
	 * @return
	 */
	public static String get_client_message(String openid,String msgtype,String  content){
		JSONObject client_message=new JSONObject();
		client_message.put("touser", openid);
		client_message.put("msgtype", msgtype);
		JSONObject json_content=new JSONObject();
		json_content.put("content", content);
		client_message.put("text", json_content);
		return client_message.toString();
	}
	

	
	
	public static String get_templt_data(String openid,String templt_id,String data){
		
		
		
		
		JSONObject send_data = new JSONObject();
		send_data.put("touser", openid);
		send_data.put("template_id", templt_id);
		send_data.put("url", "http://localhost/wx2");
		
		
		
		JSONObject first = new JSONObject();
		first.put("color", "#173177");
		first.put("value", "first");
		
		
		JSONObject OrderSn = new JSONObject();
		OrderSn.put("color", "#173177");
		OrderSn.put("value", "1111");
		
		JSONObject OrderStatus= new JSONObject();
		OrderStatus.put("color", "#173177");
		OrderStatus.put("value", " 已收货");
		
		JSONObject remark= new JSONObject();
		remark.put("color", "#173177");
		remark.put("value", "这是备注，http://localhost/wx2");
		
		JSONObject data1 = new JSONObject();
		data1.put("OrderSn", OrderSn);
		data1.put("OrderStatus", OrderStatus);
		data1.put("remark", remark);
		data1.put("first", first);
		
		
		send_data.put("data", data1);
		
		return send_data.toString();
	}
	
	
	/**
	 * 审核提醒
	 * 
	 * 模板id：r034vrL_04MhO7Jyvco6Xp5KOZvSLRLPIRo0kYt42yU
	 * 
	 * 这个消息 模板  我们用于发消息吧。
	 * 
	 * @author {{first.DATA}}
	 * @author 消息类别：{{keyword1.DATA}}
	 * @author 通知用户：{{keyword2.DATA}}
	 * @author 通知内容：{{keyword3.DATA}}
	 * @author 通知时间：{{keyword4.DATA}}
	 * @author {{remark.DATA}}
	 * 
	 */
	public static String get_r034vrL_04MhO7Jyvco6Xp5KOZvSLRLPIRo0kYt42yU(String openid,String message){
		
		JSONObject send_data = new JSONObject();
		send_data.put("touser", openid);
		send_data.put("template_id", "r034vrL_04MhO7Jyvco6Xp5KOZvSLRLPIRo0kYt42yU");
		
		JSONObject first = new JSONObject();
		first.put("color", "#173177");
		first.put("value", "明天，早上8点开会。请大家注意了。");
		
		JSONObject data1 = new JSONObject();
		data1.put("first", first);
		
		send_data.put("data", data1);
		
		return send_data.toString();
	}
	
	
	/**
	 * 
	 * @param {{first.DATA}}
	 * @param 任务名称：{{keyword1.DATA}}
	 * @param 相关人员：{{keyword2.DATA}}
	 * @param {{remark.DATA}}
	 * @return
	 */
	public static String get_OPENTM205196607(String openid,String first_,String keyword1_,String keyword2_){
		JSONObject send_data = new JSONObject();
		send_data.put("touser", openid);
		send_data.put("template_id", "P5lcHukoqT3IDSnvEdUne0k77iCWYi76b-Om_2CYQCQ");
		
		
		JSONObject first = new JSONObject();
		first.put("color", "#173177");
		first.put("value", first_);
		
		JSONObject keyword1 = new JSONObject();
		keyword1.put("color", "#173177");
		keyword1.put("value", keyword1_);
		
		
		JSONObject keyword2 = new JSONObject();
		keyword2.put("color", "#173177");
		keyword2.put("value", keyword2_);
		
		JSONObject data1 = new JSONObject();
		data1.put("first", first);
		data1.put("keyword1", keyword1);
		data1.put("keyword2", keyword2);
		
		send_data.put("data", data1);
		
		return send_data.toString();
	}
	
	
	
	/*
	 * 临时二维码，如果用户未关注  则让用户关注 。关于后，会推送我们设置的scene_id表明用户从哪里来的。
	 * 
	 * 
	 * 这里临时二维码需要改动的是expire_seconds后面的数字，表示时间。最大值为30天，这里请换算成秒表示。
	 * 还需改动的是scene_id，
	 * 自行输入一个1-100000之间的整数（这个就是你的二维码参数了，就是用这个数字给二维码编号的意思，
	 * 用户扫描二维码以后会返回这个数字给你，
	 * 表明用户是用通过此渠道关注你的，这样就可以统计用户信息与不同渠道宣传效果。）
	         {
		      "expire_seconds": 10000,
		      "action_name": "QR_SCENE",
		      "action_info": {
		          "scene": {
		             "scene_id": 100000
		          }
		       }
		      }
	*/
	public static String getTempTicketData(){
		JSONObject ticket_data = new JSONObject();
		JSONObject action_info = new JSONObject();
		JSONObject scene = new JSONObject();
		scene.put("scene_id", 100000);
		action_info.put("scene", scene);
		ticket_data.put("expire_seconds", 1800);
		ticket_data.put("action_name", "QR_SCENE");
		ticket_data.put("action_info", action_info);
		return ticket_data.toString();
	}
	
	/**
	 * {
	    "action_name": "QR_LIMIT_SCENE",
	    "action_info": {
	        "scene": {
	            "scene_id": 1000   【1-100000】
	        }
	    }
	}
	 * @return 永久ticket
	 */
	public static String getTicketData(){
		JSONObject ticket_data = new JSONObject();
		JSONObject action_info = new JSONObject();
		JSONObject scene = new JSONObject();
		scene.put("scene_id", 1000);
		action_info.put("scene", scene);
		ticket_data.put("action_name", "QR_LIMIT_SCENE");
		ticket_data.put("action_info", action_info);
		return ticket_data.toString();
		
	}
}
