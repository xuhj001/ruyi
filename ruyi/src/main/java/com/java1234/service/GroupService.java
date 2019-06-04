package com.java1234.service;

import java.util.List;
import java.util.Map;
import com.java1234.entity.Group;
import com.java1234.entity.User;

public interface GroupService {
	
	
	public int add(Group group);
	
	public int update(Group group);
	
	public List<Group> list(Map<String,Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public Group findById_(String id_);
	
	/**
	 * 删除用户  
	 * @param id
	 * 这里有个小bug  删除用户要先删除  membership然后才可以删除用户表。  因为他们是关键关系。  
	 */
	public int delete(String id_);
	
	
	//查询用户已拥有的权限
		public List<Group> listByUserId(String userId);
	
}
