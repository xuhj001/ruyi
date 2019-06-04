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
	 * 删除renwu表和rwidtt表
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * 这个接口是给用户用的   创建人or绑定人 
	 * @param map
	 * @return
	 */
	public List<RenWu> list2(Map<String, Object> map);

	
	//任务的占单  service
	public void update_accept(String taskId, String taskName);

	
	//完成任务
	public void done(String taskId, String taskName, String comment, String msg);

	public void last_done(String taskId, String taskName, String comment, String msg, RenWu renwu);
	
	
	
	
	
}
