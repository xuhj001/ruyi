package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.RenWu;
import com.java1234.entity.SMSTask;
import com.java1234.entity.User;


public interface SMSTaskService {
	
	public Integer add(SMSTask smsTask);
	
	public Integer delete(Integer id);
	
	public List<SMSTask> list(Map<String,Object> map);
	
	
	/**
	 * �������  ����  �ر�  �����ŵķ���
	 * ����taskName
	 * @˵��   �����������  ��������  ռ������
	 * 
	 */
	public void completeAfterSendSMS(String taskName,String msg,String procdefId,String  type,RenWu renwu,User currentUser  );
	
	
	
}
