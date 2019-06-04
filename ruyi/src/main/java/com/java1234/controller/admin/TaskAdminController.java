package com.java1234.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java1234.entity.Assigned;
import com.java1234.entity.MyTask;
import com.java1234.entity.PageBean;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.service.AssignedService;
import com.java1234.service.PublicService;
import com.java1234.service.RenWuService;
import com.java1234.util.ResponseUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/task")
public class TaskAdminController {
	
	
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
	private PublicService publicService  ;
	@Resource
	private AssignedService assignedService;
	@Resource
	private RenWuService renwuService ;
	
	/**
	 * 这个是【待办任务】，没有人办理的任务
	 * 另外还有【已分配】，已经有人在办理的任务
	 */
	@RequestMapping("/weifenpei")
	public String weifenpei(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="s_name",required=false)String s_name,
			@RequestParam(value="prodefkey",required=false)String prodefkey,
			HttpServletResponse response)throws Exception{
		if(s_name==null){
			s_name="";
		}
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		//个人待办
		List<Task> singleList=taskService.createTaskQuery() // 创建任务查询
				.orderByTaskCreateTime().desc()//排序
				.taskAssignee(currentUser.getId_())//指定人，一个人
				//.taskCandidateUser(currentUser.getId_()) // 根据用户id查询 多用户用这个。
				.processDefinitionKey(prodefkey)//根据流程查
				.taskNameLike("%"+s_name+"%") // 根据任务名称查询
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // 返回带分页的结果集合
		
		//组待办
		List<Task> groupList=taskService.createTaskQuery() // 创建任务查询
				.orderByTaskCreateTime().desc()//排序
				//.taskAssignee(currentUser.getId_())//指定人，一个人
				.taskCandidateUser(currentUser.getId_()) // 根据用户id查询 多用户用这个。
				.processDefinitionKey(prodefkey)//根据流程查
				.taskNameLike("%"+s_name+"%") // 根据任务名称查询
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // 返回带分页的结果集合
		
		
		//待分配【没有人办理】未分配 
		List<MyTask> weifenpeiList=new ArrayList<MyTask>();
		
		
		
		Assigned yifenpei = null;
		RenWu renwu = null ;
		for(Task task:singleList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei==null){
				//根据任务id拿流程变量  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());
				myTask.setBanliren("无");
				myTask.setRenwuFenlei("个人任务");
				weifenpeiList.add(myTask);
			}
		}
		
		for(Task task:groupList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei==null){
				//根据任务id拿流程变量  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());
				myTask.setBanliren("无");
				myTask.setRenwuFenlei("部门任务");
				weifenpeiList.add(myTask);
			}
		}
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.clear();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		map.put("rows", weifenpeiList);
		map.put("total", weifenpeiList.size());
		ResponseUtil.write(response, gson.toJson(map));
		 
		return null;
	}
	
	
	/**
	 * 这个是【已分配任务】，有人办理中的任务
	 * 
	 */
	@RequestMapping("/yifenpei")
	public String yifenpei(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,String s_name,
			@RequestParam(value="prodefkey",required=false)String prodefkey,
			HttpServletResponse response)throws Exception{
		if(s_name==null){
			s_name="";
		}
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		//个人待办
		List<Task> singleList=taskService.createTaskQuery() // 创建任务查询
				.orderByTaskCreateTime().desc()//排序
				.taskAssignee(currentUser.getId_())//指定人，一个人
				//.taskCandidateUser(currentUser.getId_()) // 根据用户id查询 多用户用这个。
				.processDefinitionKey(prodefkey)//根据流程查
				.taskNameLike("%"+s_name+"%") // 根据任务名称查询
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // 返回带分页的结果集合
		
		
		//组待办
		List<Task> groupList=taskService.createTaskQuery() // 创建任务查询
				.orderByTaskCreateTime().desc()//排序
				//.taskAssignee(currentUser.getId_())//指定人，一个人
				.taskCandidateUser(currentUser.getId_()) // 根据用户id查询 多用户用这个。
				.processDefinitionKey(prodefkey)//根据流程查
				.taskNameLike("%"+s_name+"%") // 根据任务名称查询
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // 返回带分页的结果集合
		

		
		//已分配【正在办理】的任务
		List<MyTask> yifenpeiList=new ArrayList<MyTask>();
		
		Assigned yifenpei = null;
		RenWu renwu = null;
		for(Task task:singleList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei!=null){
				//根据任务id拿流程变量  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());//这是任务的创建时间
				myTask.setBanliren(renwu.getAcceptUser().getFirst_());
				myTask.setRenwuFenlei("个人任务");
				yifenpeiList.add(myTask);
			}
		}
		
		for(Task task:groupList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei!=null){
				//根据任务id拿流程变量  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());//这是任务的创建时间
				myTask.setBanliren(renwu.getAcceptUser().getFirst_());
				myTask.setRenwuFenlei("部门任务");
				yifenpeiList.add(myTask);
			}
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.clear();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		map.put("rows", yifenpeiList);
		map.put("total", yifenpeiList.size());
		
		ResponseUtil.write(response, gson.toJson(map));
		 
		return null;
	}
	
	
	
	
	
}
