package com.java1234.dao;

import java.util.List;
import java.util.Map;
import com.java1234.entity.RenWuIdentity;



public interface RenWuIdentityDao {
	
	public Integer add(RenWuIdentity rwidtt);
	
	public List<RenWuIdentity> list(Map<String, Object> map);
	
	public Integer delete(Integer renwuId);
}
