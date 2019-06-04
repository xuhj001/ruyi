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
			//ɾ��������
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
		
		//��������id�����̱���  shouhouId
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu  =this.findById(renwuId);
		
		renwu.setIsLock(2);//2��ס
		renwu.setAcceptUserId(currentUser.getId_());
		renwu.setAcceptDateTime(new Date());
		this.update(renwu);
		//���� ռ��������
		
		
		//����taskname ���̶���id  ��type  1���  2����    ȡ���Ƿ���ʾ����
		String phoneText = showPhoneService.getPhone(taskName,"2", renwu, currentUser);
		
		
		//��� ��ע 
		String comment="���ܴ�����";
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);
		
		//��ӵ��ѷ���
		assignedService.add(taskId);	
		
		
		//��ѯ ������������  
		sMSTaskService.completeAfterSendSMS(taskName,null,renwu.getProcessDefinitionId(), "2", renwu, currentUser);
		
		//��ѯ ������������
		
		//�������������ϵ��
		rwidttService.add_idt(currentUser,renwu);
		
	}
	
	
	@Override
	public void done(String taskId, String taskName, String comment, String msg) {
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//���̱���
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.clear();
		variables.put("msg", msg);
		
		//�ó� �ۺ�ʵ�� 
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu = this.findById(renwuId);
		
		
		//���ռ����
		renwu.setAcceptUserId(null);
		renwu.setAcceptDateTime(null);
		renwu.setIsLock(1);
		
		
		//����״̬  //1������    2ͨ��    3����     3=�ر�
		if(msg.equals("ͨ��")){
			renwu.setState(1);
		}
		if(msg.equals("����")){
			renwu.setState(1);
		}
		if(msg.equals("�ر�")){
			renwu.setState(3);
		}
		
		//��ѯ ������������  ����msg ��
		sMSTaskService.completeAfterSendSMS(taskName,msg, renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, msg,  renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//��ѯ ������������
		
		
		
		
		this.update(renwu);
		
		//����taskname ���̶���id  ��type(���1������2)   ȡ���Ƿ���ʾ����
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		//�����ע
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);		
		
		//�����ѯ��Ҫ����Ҫ ��Ӷ�������̱���  �������̶���id renwu.getProcessDefinitionId()   ��������taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		//������� ��ӱ���
		taskService.complete(taskId,variables); // �������   ���� ��Ӿֲ����̱���
		
		//��� �ѷ��� 
		assignedService.delete(taskId);
		
		//�������������ϵ��
		rwidttService.add_idt(currentUser,renwu);
		
		//���֮�󣬻�Ҫ��һ��  ���Ǹ���renwu��taskid��taskname
		
		//��������ʵ��Id��ѯ���� ���������ǵ�һ���ڵ�����ӣ���������
		Task task=taskService.createTaskQuery().processInstanceId(renwu.getProcessInstanceId()).singleResult();
		
		if(task==null){
			System.out.println("�Ѿ�û�������ˣ����ã�stateΪ3");
			renwu.setState(3);
			this.update(renwu);
		}
		
	}
	
	
	@Override
	public void last_done(String taskId, String taskName, String comment, String msg, RenWu renwu) {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//���̱���
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.clear();
		variables.put("msg", msg);
		
		//���ռ����
		renwu.setAcceptUserId(null);
		renwu.setAcceptDateTime(null);
		renwu.setIsLock(1);
		
		//����״̬   1������    2ͨ��    3����     3=�ر�
		if(msg.equals("ͨ��")){
			renwu.setState(2);
			//�ж������ǲ�������
			if(renwu.getXiaoshou()!=null){
				XiaoShou xiaoShou = renwu.getXiaoshou();
				xiaoShou.setLast_change_xin_dataTime(new Date());
				xiaoShouService.update(xiaoShou);
			}
		}
		if(msg.equals("����")){
			renwu.setState(1);
		}
		if(msg.equals("�ر�")){
			renwu.setState(3);
		}
		this.update(renwu);
		
		
		//��ѯ ������������  
		sMSTaskService.completeAfterSendSMS(taskName, msg,renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		weiXinMSGService.completeAfterSendWXmsg(taskName, msg,renwu.getProcessDefinitionId(), "1", renwu, currentUser);
		//��ѯ ������������
		
		
		//����taskname ���̶���id  ��type  ȡ���Ƿ���ʾ����
		String phoneText = showPhoneService.getPhone(taskName,"1", renwu, currentUser);
		
		//�����ע
		publicService.addComment(taskId, currentUser, comment+phoneText,taskName);
		
		
		//�����ѯ��Ҫ����Ҫ ��Ӷ�������̱���  �������̶���id renwu.getProcessDefinitionId()   ��������taskName
		processVariableService.setProceVar(variables, renwu.getProcessDefinitionId(), taskName, renwu, currentUser);
		
		
		//������� ��ӱ���
		taskService.complete(taskId,variables);
		
		//����ѷ��� 
		assignedService.delete(taskId);
		
		
		//�������������ϵ��
		rwidttService.add_idt(currentUser,renwu);
		
	}

}
