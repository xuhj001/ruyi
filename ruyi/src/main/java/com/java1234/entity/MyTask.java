package com.java1234.entity;

import java.util.Date;

public class MyTask {
	
	private String id; // ����id
	private String taskName; // ��������
	private Date taskCreateTime; // ��������ڵ��ʱ��   �ϸ��ڵ�Ľ���ʱ�� ��������ʱ��
	private String banliren;//������
	private String renwuFenlei;//������  ��������
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
