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
	 * ����ǡ��������񡿣�û���˰��������
	 * ���⻹�С��ѷ��䡿���Ѿ������ڰ��������
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
		
		//���˴���
		List<Task> singleList=taskService.createTaskQuery() // ���������ѯ
				.orderByTaskCreateTime().desc()//����
				.taskAssignee(currentUser.getId_())//ָ���ˣ�һ����
				//.taskCandidateUser(currentUser.getId_()) // �����û�id��ѯ ���û��������
				.processDefinitionKey(prodefkey)//�������̲�
				.taskNameLike("%"+s_name+"%") // �����������Ʋ�ѯ
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // ���ش���ҳ�Ľ������
		
		//�����
		List<Task> groupList=taskService.createTaskQuery() // ���������ѯ
				.orderByTaskCreateTime().desc()//����
				//.taskAssignee(currentUser.getId_())//ָ���ˣ�һ����
				.taskCandidateUser(currentUser.getId_()) // �����û�id��ѯ ���û��������
				.processDefinitionKey(prodefkey)//�������̲�
				.taskNameLike("%"+s_name+"%") // �����������Ʋ�ѯ
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // ���ش���ҳ�Ľ������
		
		
		//�����䡾û���˰���δ���� 
		List<MyTask> weifenpeiList=new ArrayList<MyTask>();
		
		
		
		Assigned yifenpei = null;
		RenWu renwu = null ;
		for(Task task:singleList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei==null){
				//��������id�����̱���  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());
				myTask.setBanliren("��");
				myTask.setRenwuFenlei("��������");
				weifenpeiList.add(myTask);
			}
		}
		
		for(Task task:groupList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei==null){
				//��������id�����̱���  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());
				myTask.setBanliren("��");
				myTask.setRenwuFenlei("��������");
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
	 * ����ǡ��ѷ������񡿣����˰����е�����
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
		
		//���˴���
		List<Task> singleList=taskService.createTaskQuery() // ���������ѯ
				.orderByTaskCreateTime().desc()//����
				.taskAssignee(currentUser.getId_())//ָ���ˣ�һ����
				//.taskCandidateUser(currentUser.getId_()) // �����û�id��ѯ ���û��������
				.processDefinitionKey(prodefkey)//�������̲�
				.taskNameLike("%"+s_name+"%") // �����������Ʋ�ѯ
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // ���ش���ҳ�Ľ������
		
		
		//�����
		List<Task> groupList=taskService.createTaskQuery() // ���������ѯ
				.orderByTaskCreateTime().desc()//����
				//.taskAssignee(currentUser.getId_())//ָ���ˣ�һ����
				.taskCandidateUser(currentUser.getId_()) // �����û�id��ѯ ���û��������
				.processDefinitionKey(prodefkey)//�������̲�
				.taskNameLike("%"+s_name+"%") // �����������Ʋ�ѯ
				.listPage(pageBean.getStart(), pageBean.getPageSize()); // ���ش���ҳ�Ľ������
		

		
		//�ѷ��䡾���ڰ���������
		List<MyTask> yifenpeiList=new ArrayList<MyTask>();
		
		Assigned yifenpei = null;
		RenWu renwu = null;
		for(Task task:singleList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei!=null){
				//��������id�����̱���  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());//��������Ĵ���ʱ��
				myTask.setBanliren(renwu.getAcceptUser().getFirst_());
				myTask.setRenwuFenlei("��������");
				yifenpeiList.add(myTask);
			}
		}
		
		for(Task task:groupList){
			yifenpei = assignedService.find(task.getId());
			if(yifenpei!=null){
				//��������id�����̱���  
				Integer renwuId = (Integer) taskService.getVariable(task.getId(), "renwuId");
				renwu  = renwuService.findById(renwuId);
				MyTask myTask=new MyTask();
				myTask.setRenwu(renwu);
				myTask.setId(task.getId());
				myTask.setTaskName(task.getName());
				myTask.setTaskCreateTime(task.getCreateTime());//��������Ĵ���ʱ��
				myTask.setBanliren(renwu.getAcceptUser().getFirst_());
				myTask.setRenwuFenlei("��������");
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
