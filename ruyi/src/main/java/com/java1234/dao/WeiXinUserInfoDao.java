package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.WeiXinUserInfo;


public interface WeiXinUserInfoDao {
	
	public  Integer add(WeiXinUserInfo weiXinUserInfo);
	
	public Integer update(WeiXinUserInfo weiXinUserInfo);
	
	public List<WeiXinUserInfo> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public WeiXinUserInfo findByOpenid(String openid);
	
	public Integer updateDakaCount(Map<String, Object> map);
	
	public Integer delete(String openid);
	
	
}
