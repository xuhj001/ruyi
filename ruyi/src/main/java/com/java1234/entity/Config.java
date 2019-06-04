package com.java1234.entity;

public class Config {

	private Integer id;
	private Integer da_ka_count;//打卡次数
	private String web_site_url;//网站   http://weiweizhe.com  上传组件可能用到这个配置 
	private Integer days;//换芯的周期天数
	
	
	
	
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDa_ka_count() {
		return da_ka_count;
	}
	public void setDa_ka_count(Integer da_ka_count) {
		this.da_ka_count = da_ka_count;
	}
	public String getWeb_site_url() {
		return web_site_url;
	}
	public void setWeb_site_url(String web_site_url) {
		this.web_site_url = web_site_url;
	}
	
	
	
	
}
