package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.FenDianDao;
import com.java1234.dao.GroupDao;
import com.java1234.entity.FenDian;
import com.java1234.entity.JiXing;
import com.java1234.service.FenDianService;

@Service("fenDianService")
public class FenDianServiceImpl implements FenDianService {
	
	
	@Resource
	private FenDianDao fenDianDao;
	
	@Override
	public Integer add(FenDian fendian) {
		// TODO Auto-generated method stub
		return fenDianDao.add(fendian);
	}

	@Override
	public Integer update(FenDian fendian) {
		// TODO Auto-generated method stub
		return fenDianDao.update(fendian);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fenDianDao.getTotal(map);
	}

	@Override
	public List<FenDian> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fenDianDao.list(map);
	}
	

	@Override
	public FenDian findById(Integer id) {
		// TODO Auto-generated method stub
		return fenDianDao.findById(id);
	}
	
	
	 

}
