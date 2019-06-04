package com.java1234.entity;

public class WeiXinMSG {
	
	private Integer id;
	private String taskName;//任务名称
	private String procdefId;//流程定义id
	
	//短信发送的部门  组  如果设置了部门  
	//就是部门的主键，如果没有设置就是-1
	private String groupId;       private Group group;
	private String createUser;//创建人   1发送    0不发  
	
	private Integer type;//1任务节点   2占单节点
	
	//任务点节  区分的
	//这里要区分msg    通过   驳回    关闭
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
