package com.java1234.entity;

import java.util.Date;

public class WeiXinUserInfo {
	
	private String openid;//这个openid 与  user表的id  一致   是主键
	private String nickname;//昵称
	private String sex;// 性别1男2女
	private String language;//  简体中文
	private String city;// 城市 深圳
	private String province;// 省  广东
	private String country;// 国家中国
	private String headimgurl;// 头像url
	//////////////////////////////////////
	/////////////////////////////////////
	
	private Date createDateTime; //创建时间 
	private Integer daka_count ; //今天 打卡的次数  晚上24点定时清0
	private String treeIds ; //存权限菜单
	private String trueOpenid;//这是真实的openid  因为用户本表中的openid不是，真实的openid
	
	
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
	public String getTrueOpenid() {
		return trueOpenid;
	}
	public void setTrueOpenid(String trueOpenid) {
		this.trueOpenid = trueOpenid;
	}
	public String getTreeIds() {
		return treeIds;
	}
	public void setTreeIds(String treeIds) {
		this.treeIds = treeIds;
	}
	public Integer getDaka_count() {
		return daka_count;
	}
	public void setDaka_count(Integer daka_count) {
		this.daka_count = daka_count;
	}
	 
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	
}
