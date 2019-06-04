package com.java1234.service;

import java.util.List;
import java.util.Map;
import com.java1234.entity.ProcessVariable;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;

public interface ProcessVariableService {

	
	public Integer add(ProcessVariable processVariable);
	
	public List<ProcessVariable> list(Map<String,Object> map);
	
	public Integer delete(Integer id);
	
	//�������̶���id ����������  ������Ҫ���ݵı���
	public void setProceVar(Map<String,Object> variables ,String processDefinitionId,String taskName,RenWu renwu,User currentUser);
	
	
}
