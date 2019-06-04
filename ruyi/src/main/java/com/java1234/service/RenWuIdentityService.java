package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.RenWu;
import com.java1234.entity.RenWuIdentity;
import com.java1234.entity.User;

public interface RenWuIdentityService {


	public Integer add(RenWuIdentity rwidtt);
	
	
	
	public List<RenWuIdentity> list(Map<String, Object> map);
	
	
	
	/**
	 * 添加身份联系人，  添加之前判断下有没有，没有就加。 有就不加
	 */
	public void add_idt(User currentUser,RenWu renwu);
	
	
	public Integer delete(Integer renwuId);
	
}
