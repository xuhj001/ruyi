package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.FenDian;
import com.java1234.entity.JiXing;



public interface FenDianService {
	
	
	public Integer add(FenDian fendian  );
	
	public Integer update(FenDian fendian);
	
	public Integer getTotal(Map<String,Object> map);
	
	public List<FenDian> list(Map<String,Object> map);
	
	public FenDian findById(Integer id);
	
	
}
