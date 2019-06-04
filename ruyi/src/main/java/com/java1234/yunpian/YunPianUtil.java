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
	 * �����ϿƼ���#time#,�¼�����,�ͻ�:#clientName#,��ϵ��:#connName#,�绰:#phone#,����:#title#
	 * �����ϿƼ���#time#,�¼�����,�ͻ�:#clientName#,�绰:#phone#,����:#title#	
	 */
	public static String sendMsg(String text,String mobile) throws Exception{
		String apikey = "1542bac06a68f196e803000291ee2bfe";
		String url = "https://sms.yunpian.com/v2/sms/single_send.json";
		//String text = "�����ϿƼ���"+time+",�¼�����,�ͻ�:"+clientName+",�绰:"+phone+",����:"+title+"";
		return post(apikey, text, mobile, url);
		//{"http_status_code":400,"code":4,"msg":"�ؼ�������","detail":"�ؼ��� ������ Ϊ���δʣ���������"}
		//{"code":0,"msg":"���ͳɹ�","count":1,"fee":0.05,"unit":"RMB","mobile":"18337537525","sid":15065268376}
	}
	
	
	
	/**
	 * 
	 * @param url
	 *            http://admin.sms9.net/houtai/sms.php
	 * @param cpid
	 *            cpid �û�id��5326
	 * @param password
	 *            Md5���ܺ���ַ�����md5(password_timestamp_topsky)
	 *            ���ַ�����123456_1493287129_topsky��
	 *            ����md5���ܵõ�����16a66d6cdc584e9cb7ac96e018df7f99
	 * @param timestamp
	 *            ��ǰ�� UNIX ʱ���
	 * @param channelid
	 *            ͨ��id��3501 ���ƣ�ȫ��ͨ�� �۸�10.00��
	 * @param msg
	 *            ��Ϣ����
	 * @param tele
	 *            ��������ð�Ƕ��ŷֿ������500������
	 * @param sendtime
	 *            ����ʱ�䣬����޸ñ�������ʾ��������
	 * @return ���ؽ���� ������ͳɹ����򷵻أ�success:���η��Ͷ��ű�� �������ʧ�ܣ��򷵻أ�error:��������
	 *         ��������:���ݲ�������=-1 �û�id���������=-2 ͨ��id����=-3 �ֻ��������=-4 �������ݴ���=-5
	 *         �������=-6 ��ip����=-7 δ��ǩ��=-8 ǩ����������=-9 ͨ����ͣ=-10 ��ʱ���ֹ����=-11
	 *         ʱ�������=-12 �����쳣=-13
	 *         ���ͱ�����=-14(�����������ƣ�ͬһ���ֻ��Ų��ܷ������͹�����ţ���֤��һ����ֻ���·�һ��һ��Сʱ����)
	 *         �������ݲ���ȷ=-15(������֤�����Ϊ4-8λ������)
	 * 
	 * @throws Exception
	 */
	public static String post(String apikey, String text, String mobile, String url) throws Exception {
		// ����Ĭ�ϵ�httpClientʵ��.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// ����httppost
		HttpPost httppost = new HttpPost(url);
		
		// ������������
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
