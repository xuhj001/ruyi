package com.java1234.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.java1234.dao.RenWuDao;
import com.java1234.dao.WeiXinUserInfoDao;
import com.java1234.dao.XiaoShouDao;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.entity.XiaoShou;
import com.java1234.service.ProcessVariableService;
import com.java1234.service.PublicService;
import com.java1234.service.RenWuIdentityService;
import com.java1234.service.SMSTaskService;
import com.java1234.service.ShowPhoneService;
import com.java1234.service.WeiXinMSGService;
import com.java1234.service.XiaoShouService;



@Service("xiaoShouService")
public class XiaoShouServiceImpl implements XiaoShouService {

	@Resource
	private XiaoShouDao  xiaoShouDao;
	@Resource
	private RenWuDao renWuDao;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private PublicService publicService;
	@Resource
	private ProcessVariableService  processVariableService;
	@Resource
	private SMSTaskService  sMSTaskService;
	@Resource
	private RenWuIdentityService  rwidttService;
	@Resource
	private ShowPhoneService  showPhoneService;
	@Resource
	private WeiXinMSGService  weiXinMSGService;
	
	
	
	
	@Override
	public Integer add(XiaoShou xiaoShou) {
		xiaoShouDao.add(xiaoShou);
		
		//查询流程这个定义在外面
		//查询销售流程
		List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery() // 创建流程流程定义查询
				.orderByProcessDefinitionId().desc() // 根据流程定义id降序排列
				.processDefinitionNameLike("%销售%") // 根据流程定义名称模糊查询
				.listPage(0, 100); // 返回带分页的结果集合
		
		ProcessDefinition pdef = processDefinitions.get(0);
		
		
		//产生一个订单号   对于 售后来说没有必要
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		RenWu renwu = new  RenWu();
		//添加任务实体
		renwu.setCreateDateTime(new Date());
		renwu.setCreateUserId(currentUser.getId_());
		renwu.setProcessDefinitionId(pdef.getId());
		renwu.setProcessDefinitionKey(pdef.getKey());
		renwu.setProcessDefinitionName(pdef.getName());
		renwu.setVersion_(pdef.getVersion());
		renwu.setState(1);
		renwu.setIsLock(1);
		renwu.setXiaoshouId(xiaoShou.getId());
		renwu.setFendianId(currentUser.getWeiXinUserInfo().getFendianId());
		renWuDao.add(renwu);
		xiaoShou.setRenwuId(renwu.getId());
		
		xiaoShouDao.update(xiaoShou);
		
		
		//添加流程变量 renwuId
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("renwuId", renwu.getId());
		
		
		//启动流程   并设置流程 变量
		ProcessInstance pi= runtimeService.startProcessInstanceByKey(renwu.getProcessDefinitionKey(),variables);  
		//根据流程实例Id查询任务 这个任务就是第一个节点的名子，创建任务
		Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
		
		//设置taskName 拿到第一个节点名子   创建任务
		String taskName = task.getName();
		
		//重新拿一边renwu实体
		renwu = renWuDao.findById(renwu.getId());
		
		
		//根据taskname 流程定义id  和type  1完成   2接受    取得是否显示号码
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		
		
		//添加一个批注
		String comment = currentUser.getFirst_()+":提交销售";
		publicService.addComment(task.getId(), currentUser, comment+phoneText,taskName);
		
		
		//传一个流程变量
		variables.clear();
		variables.put("msg", "通过");
		
		
		//这里查询需要不需要 添加额外的流程变量  根据流程定义id renwu.getProcessDefinitionId()   任务名称taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		
		//完成第一个任务    设置流程变量
		taskService.complete(task.getId(), variables);
		
		
		//添加任务的身份联系人
		rwidttService.add_idt(currentUser,renwu);
		
		
		//查询 并发短信任务  1任务节点
		sMSTaskService.completeAfterSendSMS(taskName,"通过", renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, "通过", renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//查询 并发短信任务
		
		
		renwu.setState(1);//1处理中
		renwu.setProcessInstanceId(pi.getProcessInstanceId());
		renWuDao.update(renwu);
		
		return 1;
		
	}

	@Override
	public Integer update(XiaoShou xiaoShou) {
		// TODO Auto-generated method stub
		return xiaoShouDao.update(xiaoShou);
	}

	@Override
	public XiaoShou findById(Integer id) {
		// TODO Auto-generated method stub
		return xiaoShouDao.findById(id);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return xiaoShouDao.delete(id);
	}
	
	 

}
