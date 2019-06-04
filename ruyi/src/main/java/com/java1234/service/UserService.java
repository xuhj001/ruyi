package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.User;

/**
 * �û�Service
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
	 * ɾ���û�  
	 * @param id
	 * �����и�Сbug  ɾ���û�Ҫ��ɾ��  membershipȻ��ſ���ɾ���û���  ��Ϊ�����ǹؼ���ϵ��  
	 */
	public int delete(String id_);
	
	public Integer setGroups(User user);
	
	
}
