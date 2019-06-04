package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.ShuiYuan;

public interface ShuiYuanDao {

	public Integer add(ShuiYuan shuiyuan );
	
	public Integer update(ShuiYuan shuiyuan );
	
	public Integer getTotal(Map<String,Object> map);
	
	public List<ShuiYuan> list(Map<String,Object> map);
	
	public ShuiYuan findById(Integer id);
	
	
}
