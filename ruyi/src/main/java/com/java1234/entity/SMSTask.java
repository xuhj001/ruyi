package com.java1234.entity;



public class SMSTask {
	
	private Integer id;
	private String taskName;//任务名称
	private String smsModel ;//短信模版
	private String procdefId;//流程定义id
	
	//短信发送的部门  组
	private String groupId;       private Group group;
	private String client ;//客户      1发送 0不发
	
	private Integer type;//1任务节点   2占单节点
	
	//任务点节  区分的
	//这里要区分msg    通过   驳回    关闭
	private String msg;
	
	private String wx_sms;
	private String phone_sms;
	
	
	
	public String getWx_sms() {
		return wx_sms;
	}
	public void setWx_sms(String wx_sms) {
		this.wx_sms = wx_sms;
	}
	public String getPhone_sms() {
		return phone_sms;
	}
	public void setPhone_sms(String phone_sms) {
		this.phone_sms = phone_sms;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
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
	public String getSmsModel() {
		return smsModel;
	}
	public void setSmsModel(String smsModel) {
		this.smsModel = smsModel;
	}
	public String getProcdefId() {
		return procdefId;
	}
	public void setProcdefId(String procdefId) {
		this.procdefId = procdefId;
	}
	
	
	
	
}
