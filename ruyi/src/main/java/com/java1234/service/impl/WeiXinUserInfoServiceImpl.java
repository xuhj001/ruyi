package com.java1234.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.WeiXinUserInfoDao;
import com.java1234.entity.WeiXinUserInfo;
import com.java1234.service.WeiXinUserInfoService;

@Service("weiXinUserInfoService")
public class WeiXinUserInfoServiceImpl implements WeiXinUserInfoService {

	
	
	@Resource
	private WeiXinUserInfoDao  weiXinUserInfoDao;
	
	
	public Integer add(WeiXinUserInfo weiXinUserInfo) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.add(weiXinUserInfo);
	}

	public Integer update(WeiXinUserInfo weiXinUserInfo) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.update(weiXinUserInfo);
	}

	public List<WeiXinUserInfo> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.getTotal(map);
	}
	
	public WeiXinUserInfo findByOpenid(String openid) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.findByOpenid(openid);
	}
	
	public Integer updateDakaCount(Map<String, Object> map) {
		return weiXinUserInfoDao.updateDakaCount(map);
	}

	public Integer delete(String openid) {
		// TODO Auto-generated method stub
		return weiXinUserInfoDao.delete(openid);
	}

	
}
