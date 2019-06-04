package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.GuZhang;

public interface GuZhangDao {

	public Integer add(GuZhang guzhang);
	
	public Integer update(GuZhang guzhang);
	
	public Integer getTotal(Map<String,Object> map);
	
	public List<GuZhang> list(Map<String,Object> map);
	
	public GuZhang findById(Integer id);
	
}
