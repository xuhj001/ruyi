package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.SMSTask;

public interface SMSTaskDao {

	public Integer add(SMSTask smsTask);
	
	public Integer delete(Integer id);
	
	public List<SMSTask> list(Map<String,Object> map);
	
}
