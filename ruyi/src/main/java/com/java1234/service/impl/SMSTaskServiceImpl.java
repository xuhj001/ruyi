package com.java1234.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.SMSTaskDao;
import com.java1234.entity.RenWu;
import com.java1234.entity.SMSTask;
import com.java1234.entity.User;
import com.java1234.service.PublicService;
import com.java1234.service.SMSTaskService;
import com.java1234.util.SMSTaskUtil;



@Service("smsTaskService")
public class SMSTaskServiceImpl implements SMSTaskService {
	
	
	@Resource
	private SMSTaskDao  smsTaskDao;
	@Resource
	private PublicService publicService;
	
	
	public Integer add(SMSTask smsTask) {
		return smsTaskDao.add(smsTask);
	}
	
	public Integer delete(Integer id) {
		return smsTaskDao.delete(id);
	}
	
	public List<SMSTask> list(Map<String, Object> map) {
		return smsTaskDao.list(map);
	}
	
	
	@Override
	public void completeAfterSendSMS(String taskName, String msg, String procdefId, String type, RenWu renwu,
			User currentUser) {
		
		//查询发磅短信任务  根据 taskName和 procdefId 查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskName", taskName);
		map.put("procdefId", renwu.getProcessDefinitionId());
		map.put("type", type);
		map.put("msg", msg);
		List<SMSTask> TaskList = this.list(map);
		for(SMSTask smsTask :TaskList){
			if(smsTask.getPhone_sms().equals("1")){
				//拿出短信模板，填充内容
				String smsModel=smsTask.getSmsModel();
				List<String> worldList = new ArrayList<String>();//需要更换的词
				Pattern pat =  Pattern.compile("#(.*?)#");
		        Matcher m= pat.matcher(smsModel);  
		        while(m.find()){
		             String var = m.group(1);
		             worldList.add(var);//收集词
		        }
		        //传入  worldList     smsModel renwu  current user   添加smstask 取groupname
		        smsModel =  SMSTaskUtil.replace(smsModel,worldList,renwu,currentUser,smsTask);
		        //拿所有的 人员名单 
		        Map<String,Object> queryMP  = new HashMap<String,Object>();
		        queryMP.put("groupId", smsTask.getGroupId());
		        List<String> persons = publicService.getPerson(queryMP);
		        
		        for(String i:persons){
		        	try {
		        		System.out.println("号码"+ i+"-------内容:"+smsModel);
						//YunPianUtil.sendMsg(smsModel, i);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
			}
		}
		
		
		
	}

}
