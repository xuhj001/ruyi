package com.java1234.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.ShowPhoneDao;
import com.java1234.entity.RenWu;
import com.java1234.entity.ShowPhone;
import com.java1234.entity.User;
import com.java1234.service.ShowPhoneService;

@Service("showPhoneService")
public class ShowPhoneServiceImpl implements ShowPhoneService {

	@Resource
	private ShowPhoneDao showPhoneDao;

	public Integer add(ShowPhone showPhone) {
		return showPhoneDao.add(showPhone);
	}

	public Integer delete(Integer id) {
		return showPhoneDao.delete(id);
	}

	public List<ShowPhone> list(Map<String, Object> map) {
		return showPhoneDao.list(map);
	}

	@Override
	public String getPhone(String taskName, String type, RenWu renwu, User currentUser) {
		Map<String, Object> map= new HashMap<String,Object>();
		map.put("taskName", taskName);
		map.put("procdefId", renwu.getProcessDefinitionId());
		map.put("type", type);
		List<ShowPhone> showPhones = this.list(map);
		if(showPhones.size()==0){
			return "";
		}
		
		ShowPhone t = showPhones.get(0);
		
		if(t.getCurrentUser()!=null){
			return "["+currentUser.getFirst_() +":<a href=\"tel:"+currentUser.getLast_()+"\">"+currentUser.getLast_()+"</a>]";
		}
		
		
		return "";
	}

}
