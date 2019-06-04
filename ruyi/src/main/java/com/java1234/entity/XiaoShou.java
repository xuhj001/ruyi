package com.java1234.entity;

import java.math.BigDecimal;
import java.util.Date;

public class XiaoShou {
	
	private Integer id;
	private String clientName;
	private String clientPhone;
	private String clientAddress;
	
	private Integer jixingId;//机型
	private JiXing jixing;
	
	private Integer shuiyuanId;//水源
	private ShuiYuan shuiyuan;
	
	private String shuiya;//水压
	
	private Integer installPosId;//安装位置
	private InstallPos installPos;
	
	private String install_ ;//安装  不安装 
	
	private BigDecimal ding_jine;//定金
	
	private BigDecimal yu_jine;//余款
	private String remark;//备注
	private Integer renwuId;
	//弄个状态记录  是正在交往还是已转为流程
	private String baidu_x;
	private String baidu_y;
	private String  ticheng;//提成 不提成    默认不提成
	private BigDecimal ticheng_jine;//提成金额    默认0
	
	//最后一次换芯时间  如果是新建的客户  默认是创建时间
	private Date last_change_xin_dataTime;
	
 
	

	public Date getLast_change_xin_dataTime() {
		return last_change_xin_dataTime;
	}

	public void setLast_change_xin_dataTime(Date last_change_xin_dataTime) {
		this.last_change_xin_dataTime = last_change_xin_dataTime;
	}

	public String getTicheng() {
		return ticheng;
	}

	public void setTicheng(String ticheng) {
		this.ticheng = ticheng;
	}

	public BigDecimal getTicheng_jine() {
		return ticheng_jine;
	}

	public void setTicheng_jine(BigDecimal ticheng_jine) {
		this.ticheng_jine = ticheng_jine;
	}

	public Integer getJixingId() {
		return jixingId;
	}

	public void setJixingId(Integer jixingId) {
		this.jixingId = jixingId;
	}

	public JiXing getJixing() {
		return jixing;
	}

	public void setJixing(JiXing jixing) {
		this.jixing = jixing;
	}

	public Integer getShuiyuanId() {
		return shuiyuanId;
	}

	public void setShuiyuanId(Integer shuiyuanId) {
		this.shuiyuanId = shuiyuanId;
	}

	public ShuiYuan getShuiyuan() {
		return shuiyuan;
	}

	public void setShuiyuan(ShuiYuan shuiyuan) {
		this.shuiyuan = shuiyuan;
	}

	public String getShuiya() {
		return shuiya;
	}

	public void setShuiya(String shuiya) {
		this.shuiya = shuiya;
	}

	public Integer getInstallPosId() {
		return installPosId;
	}

	public void setInstallPosId(Integer installPosId) {
		this.installPosId = installPosId;
	}

	public InstallPos getInstallPos() {
		return installPos;
	}

	public void setInstallPos(InstallPos installPos) {
		this.installPos = installPos;
	}

	public String getBaidu_x() {
		return baidu_x;
	}

	public void setBaidu_x(String baidu_x) {
		this.baidu_x = baidu_x;
	}

	public String getBaidu_y() {
		return baidu_y;
	}

	public void setBaidu_y(String baidu_y) {
		this.baidu_y = baidu_y;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public BigDecimal getDing_jine() {
		return ding_jine;
	}

	public void setDing_jine(BigDecimal ding_jine) {
		this.ding_jine = ding_jine;
	}

	public String getInstall_() {
		return install_;
	}

	public void setInstall_(String install_) {
		this.install_ = install_;
	}

	public BigDecimal getYu_jine() {
		return yu_jine;
	}

	public void setYu_jine(BigDecimal yu_jine) {
		this.yu_jine = yu_jine;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRenwuId() {
		return renwuId;
	}

	public void setRenwuId(Integer renwuId) {
		this.renwuId = renwuId;
	}

	
	 
	
	
	
	
}
