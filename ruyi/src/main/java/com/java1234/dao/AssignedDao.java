package com.java1234.dao;

import com.java1234.entity.Assigned;

public interface AssignedDao {

	
	public Integer add(String taskId);
	
	public Integer delete(String taskId);
	
	public Assigned find(String taskId);
	
	
	
}
