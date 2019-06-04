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
	 * @param ���mav.addObject("leftPage", "/admin/common/left_menu.jsp");
	 * @param �Լ����leftmenu ������
	 * @param mav
	 */
	public void addLeftMenu(ModelAndView mav);
	
	/**
	 * �������̶����id ��ȫ��������ڵ�
	 * @param map
	 * @return
	 */
	public List<UserTask> getAllUserTaskByProcdefId(Map<String, Object> map);
	
	/**
	 * @��ע �����ע ��������id�ҵ�ʵ��id Ȼ�����
	 * 
	 * @��ע �º�[��������]   ������ +  ��������-Ҳ����taskName��
	 * @��ע comment ��ע����
	 * @return
	 */
	public Integer addComment(String taskId,  User user,String comment,String taskName);
	
	/**
	 * �õ������˵ĵ绰������groupid��
	 * @param queryMP
	 * @return
	 */
	public List<String> getPerson(Map<String, Object> queryMP);
	
	public List<Comment> listCommentByTaskId(String taskId);
	
	public void refreshToken();
	
	
	 
		
}
