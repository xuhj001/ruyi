package com.java1234.entity;

import java.util.Date;


public class RenWuIdentity {
	//�����޷�ֱ�Ӳ�ֵ꣬���۵���
	
	private Integer renwuId;                 private RenWu renwu;
	private String userId;                   private User user;
	//���user�� �����˵�userid
	
	private String processDefinitionKey;//shouhou   weixiu   caiwushenpi
	private Date  renwuCreateDateTime;
	
	
	//ΪʲôҪ�ѷֵ�id ���õ������ʵ�����õ�����Ҳ��ʵ�֣��ֵ��ѯ����select id from renwu where fendianid =${} ��
	//�����������и��׶ˡ�����Ժ����������ˡ���������ʱ
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
