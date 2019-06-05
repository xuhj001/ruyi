package com.java1234.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.java1234.dao.RenWuIdentityDao;
import com.java1234.entity.RenWu;
import com.java1234.entity.RenWuIdentity;
import com.java1234.entity.User;
import com.java1234.service.RenWuIdentityService;


@Service("rwidttService")
public class RenWuIdentityServiceImpl implements RenWuIdentityService {
	
	
	@Resource
	private RenWuIdentityDao    rwidttDao;
	
	
	public Integer add(RenWuIdentity rwidtt) {
		return rwidttDao.add(rwidtt);
	}
	
	
	public List<RenWuIdentity> list(Map<String, Object> map) {
		return rwidttDao.list(map);
	}

	
	
	/**
	 * 添加身份联系人，  添加之前判断下有没有，没有就加。 有就不加
	 */
	public void add_idt(User currentUser,RenWu renwu) {
		
		//根据userid 和renwuid 查查这个人有没有记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", currentUser.getId_());
		map.put("renwuId", renwu.getId());
		List<RenWuIdentity> list = this.list(map);
		if(list.size()>0){
			//如果有记录  直接返回
			return ; 
		}
		
		
		RenWuIdentity rwidtt = new RenWuIdentity();
		rwidtt.setUserId(currentUser.getId_());
		rwidtt.setRenwuId(renwu.getId());
		rwidtt.setRenwuCreateDateTime(renwu.getCreateDateTime());
		rwidtt.setProcessDefinitionKey(renwu.getProcessDefinitionKey());
		rwidtt.setFendianId(renwu.getFendianId());
		this.add(rwidtt);
	}

	

	@Override
	public Integer delete(Integer renwuId) {
		// TODO Auto-generated method stub
		return rwidttDao.delete(renwuId);
	}
	
	
	
}
