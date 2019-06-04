package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.ShowPhone;


public interface ShowPhoneDao {
	
	public Integer add(ShowPhone showPhone);
	
	public Integer delete(Integer id);
	
	public List<ShowPhone> list(Map<String,Object> map);
	
}
