package com.java1234.entity;

/**
 * �û���չʵ��
 * @author user
 *
 */
public class User {
	
	private String id_; // ���� �û���  openid
	private Integer rev_;//����  �������� �ǿͻ������ڲ���Ա   1��΢�� �û�    2���ڲ���Ա
	private String first_  ; //��ʵ����
	private String last_  ; //  �绰
	private String email_  ; // ���� �������������ʺ��ܷ���Ե�½  1�ܡ�null 0 ���ܡ�
	private String  pwd_  ; // ����
	private String  picture_id_; //����ʱ��
	private String groups; // �û�ӵ�еĽ�ɫ���� �Զ��Ÿ���
	private WeiXinUserInfo weiXinUserInfo ; //��չ������ 
	
	
	
	
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
