package com.java1234.service;

import java.util.List;
import java.util.Map;
import com.java1234.entity.RenWu;

public interface RenWuService {
	
	public Integer add(RenWu renwu);
	
	public Integer update(RenWu renwu);
	
	public List<RenWu> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public RenWu findById(Integer id);
	
	public RenWu findByDingdan_num(String dingdan_num);
	
	/**
	 * ɾ��renwu���rwidtt��
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * ����ӿ��Ǹ��û��õ�   ������or���� 
	 * @param map
	 * @return
	 */
	public List<RenWu> list2(Map<String, Object> map);

	
	//�����ռ��  service
	public void update_accept(String taskId, String taskName);

	
	//�������
	public void done(String taskId, String taskName, String comment, String msg);

	public void last_done(String taskId, String taskName, String comment, String msg, RenWu renwu);
	
	
	
	
	
}
