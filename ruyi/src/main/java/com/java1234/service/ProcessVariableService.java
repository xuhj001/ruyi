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
	
	//根据流程定义id 和任务名称  设置需要传递的变量
	public void setProceVar(Map<String,Object> variables ,String processDefinitionId,String taskName,RenWu renwu,User currentUser);
	
	
}
