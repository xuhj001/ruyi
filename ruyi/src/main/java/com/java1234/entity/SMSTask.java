package com.java1234.entity;



public class SMSTask {
	
	private Integer id;
	private String taskName;//��������
	private String smsModel ;//����ģ��
	private String procdefId;//���̶���id
	
	//���ŷ��͵Ĳ���  ��
	private String groupId;       private Group group;
	private String client ;//�ͻ�      1���� 0����
	
	private Integer type;//1����ڵ�   2ռ���ڵ�
	
	//������  ���ֵ�
	//����Ҫ����msg    ͨ��   ����    �ر�
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
