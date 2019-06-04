package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.MemberShip;

/**
 * �û���ɫ��ϵDao
 * @author user
 *
 */
public interface MemberShipDao {

	/**
	 * �û���¼
	 * ��½�ɹ��󷵻�membership  ������ͺ��� user ��group
	 */
	public MemberShip login(Map<String,Object> map);
	
	/**
	 * ɾ��ָ���û����н�ɫ
	 */
	public int  deleteAllGroupsByUserId(String userId);
	
	/**
	 * ����û�Ȩ��
	 */
	public int add(MemberShip memberShip);
	
	//�����û���Ȩ�� 
	public int  update(String userId,String groupsIds);
	
	
	
	//
	public List<MemberShip> list(Map<String,Object> map);
	
}
