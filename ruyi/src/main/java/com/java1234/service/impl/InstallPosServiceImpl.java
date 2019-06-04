package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.GroupDao;
import com.java1234.dao.InstallPosDao;
import com.java1234.entity.InstallPos;
import com.java1234.service.InstallPosService;

@Service("installPosService")
public class InstallPosServiceImpl implements InstallPosService {

	@Resource
	private InstallPosDao installPosDao;
	
	
	@Override
	public Integer add(InstallPos installPos) {
		// TODO Auto-generated method stub
		return installPosDao.add(installPos);
	}

	@Override
	public Integer update(InstallPos installPos) {
		// TODO Auto-generated method stub
		return installPosDao.update(installPos);
	}

	
	@Override
	public List<InstallPos> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return installPosDao.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return installPosDao.getTotal(map);
	}
	
	
	@Override
	public InstallPos findById(Integer id) {
		// TODO Auto-generated method stub
		return installPosDao.findById(id);
	}

}
