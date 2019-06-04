package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java1234.dao.GroupDao;
import com.java1234.dao.UserDao;
import com.java1234.entity.Group;
import com.java1234.entity.User;
import com.java1234.service.UserService;
import com.java1234.service.WeiXinUserInfoService;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl implements UserService  {
	
	
	@Resource
	private UserDao userDao;
	@Resource
	private GroupDao  groupDao;
	@Resource
	private WeiXinUserInfoService weiXinUserInfoService;
	
	
	
	public User findById_(String id_) {
		return userDao.findById_(id_);
	}

	public List<User> list(Map<String, Object> map) {
		return userDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return userDao.getTotal(map);
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public int add(User user) {
		return userDao.add(user);
	}

	public int delete(String id_) {
		userDao.delete(id_);
		weiXinUserInfoService.delete(id_);
		return 1 ;
	}

	public Integer setGroups(User user) {
		
		List<Group> groupList=groupDao.listByUserId(user.getId_());
		StringBuffer groups=new StringBuffer();
		for(Group g:groupList){
			groups.append(g.getName_()+",");
		}
		if(groups.length()>0){
			//删除最后一个豆号
			user.setGroups(groups.deleteCharAt(groups.length()-1).toString());
		}else{
			user.setGroups(groups.toString());
		}
		
		return null;
	}

}
