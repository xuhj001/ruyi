package com.java1234.entity;

import java.util.Date;

/**
 * 流程 管理
 * 
 * @author Administrator
 *
 */

public class RenWu {
	
	private Integer id;
	private String dingdan_num;//订单号
	
	private Date createDateTime;// 流程 创建时间
	
	// 任务创建人
	private String createUserId;
	private User createUser;
	
	private String processInstanceId; // 流程实例id【可以查历史批注 和执行过程】
	private Integer isLock;// 1没有锁 2锁住 //加一个是否锁住， 如果有人办理，就锁住别人不能办理
	
	private Integer state;// 1处理中  2通过   3=关闭
	
	private String acceptUserId;// 任务目前是否有人占坑
	private User acceptUser; // 任务目前是否有人占坑
	
	private Date acceptDateTime;// 占坑 时间
	
	// 查询是按流程定义key查询
	private String processDefinitionKey;// 流程定义key procdef
	private String processDefinitionName;// 流程定义name 相当于任务类型
	private Integer version_; // 记录发起任务的版本 有key还是不行。不同版本
	private String processDefinitionId;// 流程定义id，可以查出来所有的任务点节，然后根据节点名子吻合下
										// 如果吻合到了，就找这个节点有没有短信需要
	
	// 微信 使用 用户查过订单后 绑定的人
	private String bindUserId;
	private User bindUser;
	
	//售后
	private Integer xiaoshouId;
	private XiaoShou xiaoshou; 
	
	//分店
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
