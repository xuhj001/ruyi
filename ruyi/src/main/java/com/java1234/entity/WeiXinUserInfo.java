package com.java1234.entity;

import java.util.Date;

public class WeiXinUserInfo {
	
	private String openid;//���openid ��  user���id  һ��   ������
	private String nickname;//�ǳ�
	private String sex;// �Ա�1��2Ů
	private String language;//  ��������
	private String city;// ���� ����
	private String province;// ʡ  �㶫
	private String country;// �����й�
	private String headimgurl;// ͷ��url
	//////////////////////////////////////
	/////////////////////////////////////
	
	private Date createDateTime; //����ʱ�� 
	private Integer daka_count ; //���� �򿨵Ĵ���  ����24�㶨ʱ��0
	private String treeIds ; //��Ȩ�޲˵�
	private String trueOpenid;//������ʵ��openid  ��Ϊ�û������е�openid���ǣ���ʵ��openid
	
	
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
