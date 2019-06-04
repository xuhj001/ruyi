package com.java1234.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.task.Comment;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.User;
import com.java1234.entity.UserTask;


public interface PublicService {

	
	/**
	 * 
	 * @param 添加mav.addObject("leftPage", "/admin/common/left_menu.jsp");
	 * @param 以及添加leftmenu 的内容
	 * @param mav
	 */
	public void addLeftMenu(ModelAndView mav);
	
	/**
	 * 根据流程定义的id 查全部的任务节点
	 * @param map
	 * @return
	 */
	public List<UserTask> getAllUserTaskByProcdefId(Map<String, Object> map);
	
	/**
	 * @备注 添加批注 根据任务id找到实例id 然后添加
	 * 
	 * @备注 陈豪[创建任务]   【姓名 +  任务名称-也就是taskName】
	 * @备注 comment 批注内容
	 * @return
	 */
	public Integer addComment(String taskId,  User user,String comment,String taskName);
	
	/**
	 * 拿到所有人的电话【根据groupid】
	 * @param queryMP
	 * @return
	 */
	public List<String> getPerson(Map<String, Object> queryMP);
	
	public List<Comment> listCommentByTaskId(String taskId);
	
	public void refreshToken();
	
	
	 
		
}
