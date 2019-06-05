package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.User;

/**
 * 用户Service
 * @author user
 *
 */
public interface UserService {

	
	public int add(User user);
	
	public int update(User user);
	
	public List<User> list(Map<String,Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public User findById_(String id_);
	/**
	 * 删除用户  
	 * @param id
	 * 这里有个小bug  删除用户要先删除  membership然后才可以删除用户表。  因为他们是关键关系。  
	 */
	public int delete(String id_);
	
	public Integer setGroups(User user);
	
	
}
