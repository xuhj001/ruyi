package com.java1234.entity;

import java.util.Date;

/**
 * ���� ����
 * 
 * @author Administrator
 *
 */

public class RenWu {
	
	private Integer id;
	private String dingdan_num;//������
	
	private Date createDateTime;// ���� ����ʱ��
	
	// ���񴴽���
	private String createUserId;
	private User createUser;
	
	private String processInstanceId; // ����ʵ��id�����Բ���ʷ��ע ��ִ�й��̡�
	private Integer isLock;// 1û���� 2��ס //��һ���Ƿ���ס�� ������˰�������ס���˲��ܰ���
	
	private Integer state;// 1������  2ͨ��   3=�ر�
	
	private String acceptUserId;// ����Ŀǰ�Ƿ�����ռ��
	private User acceptUser; // ����Ŀǰ�Ƿ�����ռ��
	
	private Date acceptDateTime;// ռ�� ʱ��
	
	// ��ѯ�ǰ����̶���key��ѯ
	private String processDefinitionKey;// ���̶���key procdef
	private String processDefinitionName;// ���̶���name �൱����������
	private Integer version_; // ��¼��������İ汾 ��key���ǲ��С���ͬ�汾
	private String processDefinitionId;// ���̶���id�����Բ�������е������ڣ�Ȼ����ݽڵ������Ǻ���
										// ����Ǻϵ��ˣ���������ڵ���û�ж�����Ҫ
	
	// ΢�� ʹ�� �û���������� �󶨵���
	private String bindUserId;
	private User bindUser;
	
	//�ۺ�
	private Integer xiaoshouId;
	private XiaoShou xiaoshou; 
	
	//�ֵ�
	private Integer fendianId;
	private FenDian fendian;
	
	
	public Integer getFendianId() {
		return fendianId;
	}
	
	public void setFendianId(Integer fendianId) {
		this.fendianId = fendianId;
	}
	
	public FenDian getFendian() {
		return fendian;
	}
	
	public void setFendian(FenDian fendian) {
		this.fendian = fendian;
	}

	public Integer getXiaoshouId() {
		return xiaoshouId;
	}

	public void setXiaoshouId(Integer xiaoshouId) {
		this.xiaoshouId = xiaoshouId;
	}

	public XiaoShou getXiaoshou() {
		return xiaoshou;
	}

	public void setXiaoshou(XiaoShou xiaoshou) {
		this.xiaoshou = xiaoshou;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDingdan_num() {
		return dingdan_num;
	}

	public void setDingdan_num(String dingdan_num) {
		this.dingdan_num = dingdan_num;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAcceptUserId() {
		return acceptUserId;
	}

	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	public User getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(User acceptUser) {
		this.acceptUser = acceptUser;
	}

	public Date getAcceptDateTime() {
		return acceptDateTime;
	}

	public void setAcceptDateTime(Date acceptDateTime) {
		this.acceptDateTime = acceptDateTime;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public Integer getVersion_() {
		return version_;
	}

	public void setVersion_(Integer version_) {
		this.version_ = version_;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getBindUserId() {
		return bindUserId;
	}

	public void setBindUserId(String bindUserId) {
		this.bindUserId = bindUserId;
	}

	public User getBindUser() {
		return bindUser;
	}

	public void setBindUser(User bindUser) {
		this.bindUser = bindUser;
	}

}
