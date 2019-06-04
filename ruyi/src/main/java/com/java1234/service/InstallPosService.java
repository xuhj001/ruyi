package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.InstallPos;

public interface InstallPosService {

	
	
	public Integer add(InstallPos installPos );

	public Integer update(InstallPos installPos );
	
	public List<InstallPos> list(Map<String,Object> map);
	

	public Integer getTotal(Map<String,Object> map);
	
	public InstallPos findById(Integer id);
	
	
	
}
