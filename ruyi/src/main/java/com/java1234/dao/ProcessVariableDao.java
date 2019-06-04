package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.ProcessVariable;

public interface ProcessVariableDao {
	
	public Integer add(ProcessVariable processVariable);
	
	public List<ProcessVariable> list(Map<String,Object> map);
	
	public Integer delete(Integer id);
	
}
