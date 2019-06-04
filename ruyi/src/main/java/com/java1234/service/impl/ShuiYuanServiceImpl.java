package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.JiXingDao;
import com.java1234.dao.ShuiYuanDao;
import com.java1234.entity.ShuiYuan;
import com.java1234.service.ShuiYuanService;

@Service("shuiYuanService")
public class ShuiYuanServiceImpl implements ShuiYuanService {

	@Resource
	private ShuiYuanDao shuiYuanDao;

	@Override
	public Integer add(ShuiYuan shuiyuan) {
		// TODO Auto-generated method stub
		return shuiYuanDao.add(shuiyuan);
	}

	@Override
	public Integer update(ShuiYuan shuiyuan) {
		// TODO Auto-generated method stub
		return shuiYuanDao.update(shuiyuan);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return shuiYuanDao.getTotal(map);
	}

	@Override
	public List<ShuiYuan> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return shuiYuanDao.list(map);
	}

	@Override
	public ShuiYuan findById(Integer id) {
		// TODO Auto-generated method stub
		return shuiYuanDao.findById(id);
	}

}
