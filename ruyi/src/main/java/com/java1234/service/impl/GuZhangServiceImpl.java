package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.GuZhangDao;
import com.java1234.dao.JiXingDao;
import com.java1234.entity.GuZhang;
import com.java1234.service.GuZhangService;

@Service("guZhangService")
public class GuZhangServiceImpl implements GuZhangService {

	@Resource
	private GuZhangDao guZhangDao;
	
	
	
	@Override
	public Integer add(GuZhang guzhang) {
		// TODO Auto-generated method stub
		return guZhangDao.add(guzhang);
	}

	@Override
	public Integer update(GuZhang guzhang) {
		// TODO Auto-generated method stub
		return guZhangDao.update(guzhang);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return guZhangDao.getTotal(map);
	}

	@Override
	public List<GuZhang> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return guZhangDao.list(map);
	}

	
	@Override
	public GuZhang findById(Integer id) {
		// TODO Auto-generated method stub
		return guZhangDao.findById(id);
	}

}
