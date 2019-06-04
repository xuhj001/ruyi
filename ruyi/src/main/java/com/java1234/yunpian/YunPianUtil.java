package com.java1234.yunpian;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.java1234.entity.User;

public class YunPianUtil {
	
	public static void main(String[] args) throws Exception {
	}
	
	/**
	 * 【蚂蚁科技】#time#,新加任务,客户:#clientName#,联系人:#connName#,电话:#phone#,标题:#title#
	 * 【蚂蚁科技】#time#,新加任务,客户:#clientName#,电话:#phone#,标题:#title#	
	 */
	public static String sendMsg(String text,String mobile) throws Exception{
		String apikey = "1542bac06a68f196e803000291ee2bfe";
		String url = "https://sms.yunpian.com/v2/sms/single_send.json";
		//String text = "【蚂蚁科技】"+time+",新加任务,客户:"+clientName+",电话:"+phone+",标题:"+title+"";
		return post(apikey, text, mobile, url);
		//{"http_status_code":400,"code":4,"msg":"关键词屏蔽","detail":"关键词 干你娘 为屏蔽词，不允许发送"}
		//{"code":0,"msg":"发送成功","count":1,"fee":0.05,"unit":"RMB","mobile":"18337537525","sid":15065268376}
	}
	
	
	
	/**
	 * 
	 * @param url
	 *            http://admin.sms9.net/houtai/sms.php
	 * @param cpid
	 *            cpid 用户id：5326
	 * @param password
	 *            Md5加密后的字符串：md5(password_timestamp_topsky)
	 *            则将字符串“123456_1493287129_topsky”
	 *            进行md5加密得到密码16a66d6cdc584e9cb7ac96e018df7f99
	 * @param timestamp
	 *            当前的 UNIX 时间戳
	 * @param channelid
	 *            通道id：3501 名称：全网通道 价格：10.00分
	 * @param msg
	 *            信息内容
	 * @param tele
	 *            多个号码用半角逗号分开，最多500个号码
	 * @param sendtime
	 *            发送时间，如果无该变量，表示立即发送
	 * @return 返回结果： 如果发送成功，则返回：success:本次发送短信编号 如果发送失败，则返回：error:错误描述
	 *         错误描述:传递参数错误=-1 用户id或密码错误=-2 通道id错误=-3 手机号码错误=-4 短信内容错误=-5
	 *         余额不足错误=-6 绑定ip错误=-7 未带签名=-8 签名字数不对=-9 通道暂停=-10 该时间禁止发送=-11
	 *         时间戳错误=-12 编码异常=-13
	 *         发送被限制=-14(由于网关限制，同一个手机号不能反复发送过多短信，验证码一分钟只能下发一条一个小时三条)
	 *         短信内容不正确=-15(语音验证码必须为4-8位的数字)
	 * 
	 * @throws Exception
	 */
	public static String post(String apikey, String text, String mobile, String url) throws Exception {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		formparams.add(new BasicNameValuePair("apikey", apikey));
		formparams.add(new BasicNameValuePair("text", text));
		formparams.add(new BasicNameValuePair("mobile", mobile));
		UrlEncodedFormEntity uefEntity;
		
		uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		httppost.setEntity(uefEntity);
		// System.out.println("executing request " + httppost.getURI());
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		String result = null;
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity, "UTF-8");
		}
		response.close();
		httpclient.close();
		return result;
	}


}
