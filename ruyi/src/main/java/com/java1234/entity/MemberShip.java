package com.java1234.entity;

/**
 * 用户  与  角色  的关联
 * @author user
 */

public class MemberShip {

	
	private User user; // 用户   张三
	private Group group; // 角色     学生
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}
