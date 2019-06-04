package com.java1234.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.AssignedDao;
import com.java1234.entity.Assigned;
import com.java1234.service.AssignedService;




@Service("assignedService")
public class AssignedServiceImpl implements AssignedService {
	@Resource
	private AssignedDao  assignedDao;
	
	public Integer add(String taskId) {
		return assignedDao.add(taskId);
	}
	
	public Integer delete(String taskId) {
		return assignedDao.delete(taskId);
	}
	
	public Assigned find(String taskId) {
		return assignedDao.find(taskId);
	}
	

}
