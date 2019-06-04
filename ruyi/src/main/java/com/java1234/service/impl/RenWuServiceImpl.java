package com.java1234.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import com.java1234.dao.RenWuDao;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.entity.XiaoShou;
import com.java1234.service.AssignedService;
import com.java1234.service.ProcessVariableService;
import com.java1234.service.PublicService;
import com.java1234.service.RenWuIdentityService;
import com.java1234.service.RenWuService;
import com.java1234.service.SMSTaskService;
import com.java1234.service.ShowPhoneService;
import com.java1234.service.WeiXinMSGService;
import com.java1234.service.XiaoShouService;



@Service("renWuService")
public class RenWuServiceImpl implements RenWuService {
	
	@Resource
	private RenWuDao renWuDao;
	@Resource
	private TaskService taskService;
	@Resource
	private ShowPhoneService showPhoneService;
	@Resource
	private PublicService publicService;
	@Resource
	private SMSTaskService sMSTaskService;
	@Resource
	private RenWuIdentityService rwidttService;
	@Resource
	private AssignedService assignedService;
	@Resource
	private ProcessVariableService processVariableService;
	@Resource
	private XiaoShouService xiaoShouService;
	@Resource
	private WeiXinMSGService weiXinMSGService;
	
	
	
	public Integer add(RenWu renwu) {
		return renWuDao.add(renwu);
	}
	
	public Integer update(RenWu renwu) {
		return renWuDao.update(renwu);
	}
	
	public List<RenWu> list(Map<String, Object> map) {
		return renWuDao.list(map);
	}
	
	public Integer getTotal(Map<String, Object> map) {
		return renWuDao.getTotal(map);
	}
	
	public RenWu findById(Integer id) {
		return renWuDao.findById(id);
	}
	
	public RenWu findByDingdan_num(String dingdan_num) {
		return renWuDao.findByDingdan_num(dingdan_num);
	}
	
	
	public Integer delete(Integer id) {
		renWuDao.delete(id);
		RenWu renWu = this.findById(id);
		if(renWu.getXiaoshou()!=null){
			//删除掉销售
			xiaoShouService.delete(renWu.getXiaoshouId());
		}
		return  rwidttService.delete(id);
	}
	

	public List<RenWu> list2(Map<String, Object> map) {
		return renWuDao.list2(map);
	}
	
	
	@Override
	public void update_accept(String taskId, String taskName) {
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//根据任务id拿流程变量  shouhouId
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu  =this.findById(renwuId);
		
		renwu.setIsLock(2);//2锁住
		renwu.setAcceptUserId(currentUser.getId_());
		renwu.setAcceptDateTime(new Date());
		this.update(renwu);
		//更新 占坑人数据
		
		
		//根据taskname 流程定义id  和type  1完成  2接受    取得是否显示号码
		String phoneText = showPhoneService.getPhone(taskName,"2", renwu, currentUser);
		
		
		//添加 批注 
		String comment="接受此任务";
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);
		
		//添加到已分配
		assignedService.add(taskId);	
		
		
		//查询 并发短信任务  
		sMSTaskService.completeAfterSendSMS(taskName,null,renwu.getProcessDefinitionId(), "2", renwu, currentUser);
		
		//查询 并发短信任务
		
		//添加任务的身份联系人
		rwidttService.add_idt(currentUser,renwu);
		
	}
	
	
	@Override
	public void done(String taskId, String taskName, String comment, String msg) {
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//流程变量
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.clear();
		variables.put("msg", msg);
		
		//拿出 售后实例 
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu = this.findById(renwuId);
		
		
		//清除占坑人
		renwu.setAcceptUserId(null);
		renwu.setAcceptDateTime(null);
		renwu.setIsLock(1);
		
		
		//更新状态  //1处理中    2通过    3驳回     3=关闭
		if(msg.equals("通过")){
			renwu.setState(1);
		}
		if(msg.equals("驳回")){
			renwu.setState(1);
		}
		if(msg.equals("关闭")){
			renwu.setState(3);
		}
		
		//查询 并发短信任务  根据msg 查
		sMSTaskService.completeAfterSendSMS(taskName,msg, renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, msg,  renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//查询 并发短信任务
		
		
		
		
		this.update(renwu);
		
		//根据taskname 流程定义id  和type(完成1，接受2)   取得是否显示号码
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		//添加批注
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);		
		
		//这里查询需要不需要 添加额外的流程变量  根据流程定义id renwu.getProcessDefinitionId()   任务名称taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		//完成任务 添加变量
		taskService.complete(taskId,variables); // 完成任务   并且 添加局部流程变量
		
		//清除 已分配 
		assignedService.delete(taskId);
		
		//添加任务的身份联系人
		rwidttService.add_idt(currentUser,renwu);
		
		//完成之后，还要做一步  就是更新renwu的taskid和taskname
		
		//根据流程实例Id查询任务 这个任务就是第一个节点的名子，创建任务
		Task task=taskService.createTaskQuery().processInstanceId(renwu.getProcessInstanceId()).singleResult();
		
		if(task==null){
			System.out.println("已经没有任务了，设置，state为3");
			renwu.setState(3);
			this.update(renwu);
		}
		
	}
	
	
	@Override
	public void last_done(String taskId, String taskName, String comment, String msg, RenWu renwu) {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//流程变量
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.clear();
		variables.put("msg", msg);
		
		//清除占坑人
		renwu.setAcceptUserId(null);
		renwu.setAcceptDateTime(null);
		renwu.setIsLock(1);
		
		//更新状态   1处理中    2通过    3驳回     3=关闭
		if(msg.equals("通过")){
			renwu.setState(2);
			//判断任务是不是销售
			if(renwu.getXiaoshou()!=null){
				XiaoShou xiaoShou = renwu.getXiaoshou();
				xiaoShou.setLast_change_xin_dataTime(new Date());
				xiaoShouService.update(xiaoShou);
			}
		}
		if(msg.equals("驳回")){
			renwu.setState(1);
		}
		if(msg.equals("关闭")){
			renwu.setState(3);
		}
		this.update(renwu);
		
		
		//查询 并发短信任务  
		sMSTaskService.completeAfterSendSMS(taskName, msg,renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, msg,renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//查询 并发短信任务
		
		
		//根据taskname 流程定义id  和type  取得是否显示号码
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		
		//添加批注
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);
		
		
		//这里查询需要不需要 添加额外的流程变量  根据流程定义id renwu.getProcessDefinitionId()   任务名称taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		
		//完成任务 添加变量
		taskService.complete(taskId,variables);
		
		//清除已分配 
		assignedService.delete(taskId);
		
		
		//添加任务的身份联系人
		rwidttService.add_idt(currentUser,renwu);
		
	}

}
