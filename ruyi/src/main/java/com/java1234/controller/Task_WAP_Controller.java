package com.java1234.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.PageBean;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.service.RenWuService;
import com.java1234.service.UserService;
import com.java1234.util.ResponseUtil;
import net.sf.json.JSONObject;



@Controller
@RequestMapping("/task/wap")
public class Task_WAP_Controller {
	
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private FormService formService;
	@Resource
	private HistoryService historyService;
	@Resource
	private RenWuService  renwuService;
	
	
	/**
	 * 
	 *      /task/wap/daiban
	 * 其实 就是根据-->>用户的id---找到组--根据组---查他的待办
	 * 待任务其实分2种 1=没有人办理的，2=已有人正在办理的
	 */
	@RequestMapping("/daiban")
	public ModelAndView daiban(@RequestParam(value="title",required=false)String title,
			@RequestParam(value="prodefkey",required=false)String prodefkey,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("title",title);
		mav.addObject("mui_title",title);
		mav.addObject("prodefkey",prodefkey);
		mav.addObject("page","/foreground/task/daiban.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	/**
	 * 根据任务的id  查找任务节点存的formData  =控制器的url /shouhou/shouhou_banli.html
	 * js端输出 result.url
	 */
	@RequestMapping("/getFormDataByTaskId")
	public String getFormDataByTaskId(String taskId,HttpServletResponse response)throws Exception{
		TaskFormData formData=formService.getTaskFormData(taskId);
		String url=formData.getFormKey();
		JSONObject result=new JSONObject();
		result.put("url", url);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	
	
}
