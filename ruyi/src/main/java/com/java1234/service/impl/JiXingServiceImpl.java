package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.InstallPosDao;
import com.java1234.dao.JiXingDao;
import com.java1234.entity.JiXing;
import com.java1234.service.JiXingService;


@Service("jiXingService")
public class JiXingServiceImpl implements JiXingService {

	@Resource
	private JiXingDao jiXingDao;
	
	
	@Override
	public Integer add(JiXing jixing) {
		// TODO Auto-generated method stub
		return jiXingDao.add(jixing);
	}

	@Override
	public Integer update(JiXing jixing) {
		// TODO Auto-generated method stub
		return jiXingDao.update(jixing);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return jiXingDao.getTotal(map);
	}

	@Override
	public List<JiXing> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return jiXingDao.list(map);
	}

	@Override
	public JiXing findById(Integer id) {
		// TODO Auto-generated method stub
		return jiXingDao.findById(id);
	}
	

}
