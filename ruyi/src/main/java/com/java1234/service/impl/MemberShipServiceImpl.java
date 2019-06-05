package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.MemberShipDao;
import com.java1234.entity.Group;
import com.java1234.entity.MemberShip;
import com.java1234.entity.User;
import com.java1234.service.MemberShipService;
import com.java1234.util.StringUtil;

/**
 * 用户角色关系Service实现类
 * @author user
 *
 */
@Service("memberShipService")
public class MemberShipServiceImpl implements MemberShipService{

	@Resource
	private MemberShipDao memberShipDao;  
	
	
	public MemberShip login(Map<String, Object> map) {
		return memberShipDao.login(map);
	}

	public int deleteAllGroupsByUserId(String userId) {
		return memberShipDao.deleteAllGroupsByUserId(userId);
	}

	public int add(MemberShip memberShip) {
		return memberShipDao.add(memberShip);
	}
	
	public int update(String userId, String groupsIds) {
		memberShipDao.deleteAllGroupsByUserId(userId);
		
		if(StringUtil.isNotEmpty(groupsIds)){
			String idsArr[]=groupsIds.split(",");
			for(String groupId:idsArr){
				User user=new User();
				user.setId_(userId);
				Group group=new Group();
				group.setId_(groupId);
				MemberShip memberShip=new MemberShip();
				memberShip.setUser(user);
				memberShip.setGroup(group);
				memberShipDao.add(memberShip);
			}
		}
		return 1;
	}
	

	public List<MemberShip> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberShipDao.list(map);
	}

}
