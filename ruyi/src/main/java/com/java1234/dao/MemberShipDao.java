package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.MemberShip;

/**
 * 用户角色关系Dao
 * @author user
 *
 */
public interface MemberShipDao {

	/**
	 * 用户登录
	 * 登陆成功后返回membership  这里面就含有 user 和group
	 */
	public MemberShip login(Map<String,Object> map);
	
	/**
	 * 删除指定用户所有角色
	 */
	public int  deleteAllGroupsByUserId(String userId);
	
	/**
	 * 添加用户权限
	 */
	public int add(MemberShip memberShip);
	
	//更新用户的权限 
	public int  update(String userId,String groupsIds);
	
	
	
	//
	public List<MemberShip> list(Map<String,Object> map);
	
}
