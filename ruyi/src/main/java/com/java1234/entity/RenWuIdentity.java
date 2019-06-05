package com.java1234.entity;

import java.util.Date;


public class RenWuIdentity {
	//这里无法直接查分店，销售单。
	
	private Integer renwuId;                 private RenWu renwu;
	private String userId;                   private User user;
	//这个user是 操作人的userid
	
	private String processDefinitionKey;//shouhou   weixiu   caiwushenpi
	private Date  renwuCreateDateTime;
	
	
	//为什么要把分店id 设置到这里，其实不设置到这里也能实现，分店查询，【select id from renwu where fendianid =${} 】
	//但是这样查有个弊端。如果以后，数据量大了。这样查会耗时
	private Integer fendianId;
	
	
	public Integer getFendianId() {
		return fendianId;
	}

	public void setFendianId(Integer fendianId) {
		this.fendianId = fendianId;
	}

	public RenWu getRenwu() {
		return renwu;
	}

	public void setRenwu(RenWu renwu) {
		this.renwu = renwu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRenwuCreateDateTime() {
		return renwuCreateDateTime;
	}

	public void setRenwuCreateDateTime(Date renwuCreateDateTime) {
		this.renwuCreateDateTime = renwuCreateDateTime;
	}

	public Integer getRenwuId() {
		return renwuId;
	}

	public void setRenwuId(Integer renwuId) {
		this.renwuId = renwuId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
}
