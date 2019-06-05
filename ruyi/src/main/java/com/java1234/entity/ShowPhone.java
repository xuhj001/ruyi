package com.java1234.entity;
	
	

public class ShowPhone {
	
	private Integer id;
	private String taskName;//任务名称
	private String procdefId;//流程定义id
	private String type; //依附于哪里，（完成1，接受2）
	
	//显示谁的号码
	private  String  currentUser;//当前用户
	private String client;//客户
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getProcdefId() {
		return procdefId;
	}
	public void setProcdefId(String procdefId) {
		this.procdefId = procdefId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	
	
	
	
}
