package com.java1234.entity;

public class WeiXinMSG {
	
	private Integer id;
	private String taskName;//��������
	private String procdefId;//���̶���id
	
	//���ŷ��͵Ĳ���  ��  ��������˲���  
	//���ǲ��ŵ����������û�����þ���-1
	private String groupId;       private Group group;
	private String createUser;//������   1����    0����  
	
	private Integer type;//1����ڵ�   2ռ���ڵ�
	
	//������  ���ֵ�
	//����Ҫ����msg    ͨ��   ����    �ر�
	private String msg;
	
	
	
	
	
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
