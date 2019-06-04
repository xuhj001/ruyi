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
		
		//��ѯ�����������������
		//��ѯ��������
		List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery() // �����������̶����ѯ
				.orderByProcessDefinitionId().desc() // �������̶���id��������
				.processDefinitionNameLike("%����%") // �������̶�������ģ����ѯ
				.listPage(0, 100); // ���ش���ҳ�Ľ������
		
		ProcessDefinition pdef = processDefinitions.get(0);
		
		
		//����һ��������   ���� �ۺ���˵û�б�Ҫ
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		RenWu renwu = new  RenWu();
		//�������ʵ��
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
		
		
		//������̱��� renwuId
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("renwuId", renwu.getId());
		
		
		//��������   ���������� ����
		ProcessInstance pi= runtimeService.startProcessInstanceByKey(renwu.getProcessDefinitionKey(),variables);  
		//��������ʵ��Id��ѯ���� ���������ǵ�һ���ڵ�����ӣ���������
		Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
		
		//����taskName �õ���һ���ڵ�����   ��������
		String taskName = task.getName();
		
		//������һ��renwuʵ��
		renwu = renWuDao.findById(renwu.getId());
		
		
		//����taskname ���̶���id  ��type  1���   2����    ȡ���Ƿ���ʾ����
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		
		
		//���һ����ע
		String comment = currentUser.getFirst_()+":�ύ����";
		publicService.addComment(task.getId(), currentUser, comment+phoneText,taskName);
		
		
		//��һ�����̱���
		variables.clear();
		variables.put("msg", "ͨ��");
		
		
		//�����ѯ��Ҫ����Ҫ ��Ӷ�������̱���  �������̶���id renwu.getProcessDefinitionId()   ��������taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		
		//��ɵ�һ������    �������̱���
		taskService.complete(task.getId(), variables);
		
		
		//�������������ϵ��
		rwidttService.add_idt(currentUser,renwu);
		
		
		//��ѯ ������������  1����ڵ�
		sMSTaskService.completeAfterSendSMS(taskName,"ͨ��", renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, "ͨ��", renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//��ѯ ������������
		
		
		renwu.setState(1);//1������
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
