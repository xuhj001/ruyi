package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.entity.WeiXinMSG;

import net.sf.json.JSONObject;


public interface WeiXinMSGService {
	
	

	public void completeAfterSendWXmsg(String taskName, String msg, String procdefId, String type, RenWu renwu,	User currentUser);
	
	/**
	 * ������Ϣ
	 * @param openid ����openid  rw_name  first=�������ݣ������ about
	 */
	public void push_sms(String openid,String first,String rw_name ,String about);
	
	
	/**
	 * �����߳�������Ϣ
	 */
	public void start_thread_push_msm(List<JSONObject> push_list);
	
	
}
