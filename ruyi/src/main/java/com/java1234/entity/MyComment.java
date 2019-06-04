package com.java1234.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MyComment {

	private String userId;
	private Date time;
	private String message;
	private List<String> imageList=new LinkedList<String>(); // 主要用于列表展示的缩略图

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	
	
	
	
	
}
