package com.java1234.entity;

/**
 * 用户扩展实体
 * @author user
 *
 */
public class User {
	
	private String id_; // 主键 用户名  openid
	private Integer rev_;//数字  用于区别 是客户还是内部人员   1是微信 用户    2是内部人员
	private String first_  ; //真实姓名
	private String last_  ; //  电话
	private String email_  ; // 邮箱 现在用于区别帐号能否电脑登陆  1能。null 0 不能。
	private String  pwd_  ; // 密码
	private String  picture_id_; //创建时间
	private String groups; // 用户拥有的角色集合 以逗号隔开
	private WeiXinUserInfo weiXinUserInfo ; //扩展的内容 
	
	
	
	
	public WeiXinUserInfo getWeiXinUserInfo() {
		return weiXinUserInfo;
	}
	public void setWeiXinUserInfo(WeiXinUserInfo weiXinUserInfo) {
		this.weiXinUserInfo = weiXinUserInfo;
	}
	public Integer getRev_() {
		return rev_;
	}
	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}
	public String getLast_() {
		return last_;
	}
	public void setLast_(String last_) {
		this.last_ = last_;
	}
	public String getId_() {
		return id_;
	}
	public void setId_(String id_) {
		this.id_ = id_;
	}
	public String getFirst_() {
		return first_;
	}
	public void setFirst_(String first_) {
		this.first_ = first_;
	}
	public String getEmail_() {
		return email_;
	}
	public void setEmail_(String email_) {
		this.email_ = email_;
	}
	public String getPwd_() {
		return pwd_;
	}
	public void setPwd_(String pwd_) {
		this.pwd_ = pwd_;
	}
	public String getPicture_id_() {
		return picture_id_;
	}
	public void setPicture_id_(String picture_id_) {
		this.picture_id_ = picture_id_;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	
	
	
	
	
}
