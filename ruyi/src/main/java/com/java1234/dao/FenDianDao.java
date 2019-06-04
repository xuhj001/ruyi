package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.FenDian;

public interface FenDianDao {

	
	
	public Integer add(FenDian fendian  );
	
	public Integer update(FenDian fendian);
	
	public Integer getTotal(Map<String,Object> map);
	
	public List<FenDian> list(Map<String,Object> map);
	
	public FenDian findById(Integer id);
	
	
	
	
}
