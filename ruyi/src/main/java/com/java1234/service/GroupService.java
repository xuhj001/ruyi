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
	 * ɾ���û�  
	 * @param id
	 * �����и�Сbug  ɾ���û�Ҫ��ɾ��  membershipȻ��ſ���ɾ���û���  ��Ϊ�����ǹؼ���ϵ��  
	 */
	public int delete(String id_);
	
	
	//��ѯ�û���ӵ�е�Ȩ��
		public List<Group> listByUserId(String userId);
	
}
