package com.java1234.service;

import java.util.List;
import java.util.Map;
import com.java1234.entity.JiXing;

public interface JiXingService {

	
	public Integer add(JiXing jixing);
	
	public Integer update(JiXing jixing);
	
	public Integer getTotal(Map<String,Object> map);
	
	public List<JiXing> list(Map<String,Object> map);
	
	public JiXing findById(Integer id);
	
	
	
}
