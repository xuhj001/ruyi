package com.java1234.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.activiti.engine.impl.pvm.PvmActivity;
import org.springframework.stereotype.Service;
import com.java1234.dao.ProcessVariableDao;
import com.java1234.entity.ProcessVariable;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.service.ProcessVariableService;


@Service("processVariableService")
public class ProcessVariableServiceImpl implements ProcessVariableService {

	
	@Resource
	private ProcessVariableDao processVariableDao;

	public Integer add(ProcessVariable processVariable) {
		// TODO Auto-generated method stub
		return processVariableDao.add(processVariable);
	}

	public List<ProcessVariable> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return processVariableDao.list(map);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return processVariableDao.delete(id);
	}
	
	
	
	public void setProceVar(Map<String, Object> variables, String processDefinitionId, String taskName, RenWu renwu,
			User currentUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("procdefId", processDefinitionId);
		map.put("taskName", taskName);
		//查当前节点 需要不需要设置流程变量
		List<ProcessVariable> list = this.list(map);
		for (ProcessVariable pv : list) {
			switch (pv.getVariableValue()) {
			//判断value用哪一个   name是固定的
			case "当前用户":
				variables.put(pv.getVariableName(), currentUser.getId_());
				break;
			case "是否安装":
				variables.put(pv.getVariableName(), renwu.getXiaoshou().getInstall_());
				break;
			}
		}
	}
	
	
}
