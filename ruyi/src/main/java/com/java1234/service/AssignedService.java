package com.java1234.service;

import com.java1234.entity.Assigned;

public interface AssignedService {
	
	public Integer add(String taskId);
	
	public Integer delete(String taskId);
	
	public Assigned find(String taskId);
	
}
