package com.java1234.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.ConfigDao;
import com.java1234.entity.Config;
import com.java1234.service.ConfigService;


@Service("configService")
public class ConfigServiceImpl implements ConfigService {


	@Resource
	private ConfigDao  configDao;
	
	
	@Override
	public Integer update(Config config) {
		// TODO Auto-generated method stub
		return configDao.update(config);
	}
	
	
	@Override
	public Config findById(Integer id) {
		// TODO Auto-generated method stub
		return configDao.findById(id);
	}

}
