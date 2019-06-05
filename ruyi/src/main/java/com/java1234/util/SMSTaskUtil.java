package com.java1234.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.java1234.entity.RenWu;
import com.java1234.entity.SMSTask;
import com.java1234.entity.User;

public class SMSTaskUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * renwuTitle  = 意向房源
	 * groupName  = 指定的部门
	 * likeHouse  = 意向房源
	 * renwuCreateUser = 流程的创建人
	 * renwuJine = client。jine
	 * renwuClient = renwu.getClient().getClientName()
	 * renwuClientPhone =  renwu.getClient().getClientPhone()
	 * renwuType =  renwu.getProcessDefinitionName()
	 * renwuCreateTime =  renwu.getCreateDateTime(), "yyyy-MM-dd HH:mm"
	 * nowTime =  "yyyy-MM-dd HH:mm"
	 * dingdanNum = renwu.dingdanNum
	 * currentUser =  currentUser.getFirst_()
	 * currentUserPhone =  currentUser.getLast_()
	 * payType = renwu.getClient().getPay_type()
	 * 
	 */
	public static String replace(String smsModel, List<String> worldList, RenWu renwu, User currentUser,SMSTask smsTask) {
		
		for (String item : worldList) {
			
			switch (item) {
			
			case "groupName":
				smsModel = smsModel.replaceAll("#" + item + "#",smsTask.getGroup().getName_());
				break;
				
			case "renwuCreateUser":
				smsModel = smsModel.replaceAll("#" + item + "#",renwu.getCreateUser().getFirst_());
				break;
				
				
			case "renwuType":
				smsModel = smsModel.replaceAll("#" + item + "#",renwu.getProcessDefinitionName());
				break;

			case "renwuCreateTime":
				smsModel = smsModel.replaceAll("#" + item + "#",DateUtil.formatDate(renwu.getCreateDateTime(), "yyyy-MM-dd HH:mm"));
				break;
			
			case "nowTime":
				smsModel = smsModel.replaceAll("#" + item + "#",DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
				break;
			
			case "dingdanNum":
				smsModel = smsModel.replaceAll("#" + item + "#",renwu.getDingdan_num());
				break;
			
			case "currentUser":
				smsModel = smsModel.replaceAll("#" + item + "#",currentUser.getFirst_());
				break;
				
			case "currentUserPhone":
				smsModel = smsModel.replaceAll("#" + item + "#",currentUser.getLast_());
				break;
				
			}
		}
		
		return smsModel;
	}
	
	
	 

}
