package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.RenWu;


public interface RenWuDao {
	
	public Integer add(RenWu renwu);
	
	public Integer update(RenWu renwu);
	
	public List<RenWu> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public RenWu findById(Integer id);
	
	public RenWu findByDingdan_num(String dingdan_num);
	
	public Integer delete(Integer id);
	
	
	/**
	 * ����ӿ��Ǹ��û��õ�   ������or���� 
	 * @param map
	 * @return
	 */
	public List<RenWu> list2(Map<String, Object> map);
	
	
}
