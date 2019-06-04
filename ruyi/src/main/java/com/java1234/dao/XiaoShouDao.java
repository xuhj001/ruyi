package com.java1234.dao;

import com.java1234.entity.XiaoShou;

public interface XiaoShouDao {

	public Integer add(XiaoShou xiaoShou  );
	
	public Integer update(XiaoShou xiaoShou );
	
	public XiaoShou findById(Integer id);
	
	public Integer delete(Integer id);
	
	
	
}
