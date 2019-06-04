package com.java1234.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.Group;
import com.java1234.entity.SMSTask;
import com.java1234.entity.UserTask;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;
import com.java1234.service.SMSTaskService;


@Controller
@RequestMapping("/smsTask/pc")
public class SMSTask_PC_Controller {

	@Resource
	private SMSTaskService smsTaskService  ;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private GroupService groupService ;
	@Resource
	private PublicService publicService;
	
	
	/**
	 * 根据  流程定义id 拿到所有的任务节点   type 代表  任务点节 还是占单节点
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(String procdefId,String type, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("procdefId", procdefId);
		mav.addObject("type", type);
		mav.setViewName("admin/page/SMSTask/SMSTask_manage");
		
		return mav;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add(String procdefId,String type, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//根据流程定义id，拿全部任务
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("procdefId", procdefId);
		List<UserTask> list = publicService.getAllUserTaskByProcdefId(map);
		
		//拿到 部门分组  
		map.clear();
		List<Group> groups = groupService.list(map);
		
		
		mav.addObject("list", list);
		mav.addObject("groups", groups);
		mav.addObject("procdefId", procdefId);
		mav.addObject("type", type);
		mav.setViewName("admin/page/SMSTask/add");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
}
