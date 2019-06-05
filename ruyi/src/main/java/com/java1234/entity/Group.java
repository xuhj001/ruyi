package com.java1234.entity;

/**
 * 角色实体扩展
 * @author user
 *
 */
public class Group {

	private String id_; // 主键 角色名  简称
	private Integer rev_; //排序用
	private String name_; // 名称     班长
	private String  type_;//不知道干什么用
	
	
	public String getId_() {
		return id_;
	}
	public void setId_(String id_) {
		this.id_ = id_;
	}
	public Integer getRev_() {
		return rev_;
	}
	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getType_() {
		return type_;
	}
	public void setType_(String type_) {
		this.type_ = type_;
	}
	
	
	
	 
	
	
}
