package com.java1234.entity;

import java.util.Date;

public class MyTask {
	
	private String id; // 任务id
	private String taskName; // 任务名称
	private Date taskCreateTime; // 任务到这个节点的时间   上个节点的结束时间 就是它的时间
	private String banliren;//办理人
	private String renwuFenlei;//组任务  个人任务
	private RenWu renwu;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}
	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public String getBanliren() {
		return banliren;
	}
	public void setBanliren(String banliren) {
		this.banliren = banliren;
	}
	public String getRenwuFenlei() {
		return renwuFenlei;
	}
	public void setRenwuFenlei(String renwuFenlei) {
		this.renwuFenlei = renwuFenlei;
	}
	public RenWu getRenwu() {
		return renwu;
	}
	public void setRenwu(RenWu renwu) {
		this.renwu = renwu;
	}
	
	
	
	
	
}
