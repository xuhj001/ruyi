package com.java1234.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.GroupDao;
import com.java1234.entity.Group;
import com.java1234.service.GroupService;


@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Resource
	private GroupDao groupDao;
	
	
	public int add(Group group) {
		// TODO Auto-generated method stub
		return groupDao.add(group);
	}

	public int update(Group group) {
		// TODO Auto-generated method stub
		return groupDao.update(group);
	}

	public List<Group> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupDao.getTotal(map);
	}

	public Group findById_(String id_) {
		// TODO Auto-generated method stub
		return groupDao.findById_(id_);
	}

	public int delete(String id_) {
		// TODO Auto-generated method stub
		return groupDao.delete(id_);
	}

	public List<Group> listByUserId(String userId) {
		// TODO Auto-generated method stub
		return groupDao.listByUserId(userId);
	}

	 

}
