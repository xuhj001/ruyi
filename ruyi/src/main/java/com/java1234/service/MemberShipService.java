package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.MemberShip;

/**
 * �û���ɫ��ϵService
 * @author user
 *
 */
public interface MemberShipService {

	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public MemberShip login(Map<String,Object> map);
	
	
	/**
	 * ɾ��ָ���û����н�ɫ
	 * @param userId
	 * @return
	 */
	public int  deleteAllGroupsByUserId(String userId);
	
	/**
	 * ����û�Ȩ��
	 * @param userRole
	 * @return
	 */
	public int add(MemberShip memberShip);
	
	
	public int  update(String userId,String groupsIds);
	
	
	public List<MemberShip> list(Map<String,Object> map);
	
	
}
