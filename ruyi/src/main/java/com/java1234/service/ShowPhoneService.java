package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.RenWu;
import com.java1234.entity.ShowPhone;
import com.java1234.entity.User;



public interface ShowPhoneService {
	
	public Integer add(ShowPhone showPhone);
	
	public Integer delete(Integer id);
	
	public List<ShowPhone> list(Map<String,Object> map);

	/**
	 * 
	 * @param taskName
	 * @param string
	 * @param renwu
	 * @param currentUser
	 * @return
	 */
	public String getPhone(String taskName, String type, RenWu renwu, User currentUser);
	
	
	
}
