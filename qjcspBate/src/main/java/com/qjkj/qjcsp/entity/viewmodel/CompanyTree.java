package com.qjkj.qjcsp.entity.viewmodel;

import java.util.Date;

import com.qjkj.qjcsp.util.DateFormat;

public class CompanyTree {
	private Long companyId;  
	
	private Long id;	//公司id
	
	private Long pid;	//父公司编号
	
	private String companyName;	//公司名称
	
	private String pCompanyName;	//父公司名称
	
	private String companyCode;		//公司编号
	
	private String companyAlias;	//公司简称
	
	private String companyPrefix;	//公司前缀
	
	private String provCode;	//省份编码
	
	private String province;	//省份
	
	private String cityCode;	//城市编码
	
	private String cityName;	//城市
	
	private String areaCode;	//地区编码
	
	private String areaName;	//地区
	
	private String address;		//地址
	
	private String corporation;	//法人
	
	private String cardNo;		//账户
	
	private String backNo;		//开户行
	
	private String backName;	//支行
	
	private String cardName;	//户名
	
	private String telautogram;	//传真
	
	private String tel;			//电话
	
	private String email;		//邮箱
	
	private String companyStatus;//公司状态
	
	private String createtime;	//创建日期
	
	private String lastModTime;	//修改日期
	
	private Long createUserId;	//创建人Id
	
	private Long modUserId;		//修改人Id
	
	private String state;		//树形菜单点击状态
	
	private Double latitude;	//纬度
    
    private Double longitude;	//经度
    
    private String businessHours;	//经营时间
    
    private String createUser;	//创建人
    
    private String modUser;	//修改人
    
    
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyAlias() {
		return companyAlias;
	}

	public void setCompanyAlias(String companyAlias) {
		this.companyAlias = companyAlias;
	}

	public String getCompanyPrefix() {
		return companyPrefix;
	}

	public void setCompanyPrefix(String companyPrefix) {
		this.companyPrefix = companyPrefix;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBackNo() {
		return backNo;
	}

	public void setBackNo(String backNo) {
		this.backNo = backNo;
	}

	public String getBackName() {
		return backName;
	}

	public void setBackName(String backName) {
		this.backName = backName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getTelautogram() {
		return telautogram;
	}

	public void setTelautogram(String telautogram) {
		this.telautogram = telautogram;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = DateFormat.dateFormatYMDHMS(createtime);
	}

	public String getLastModTime() {
		return lastModTime;
	}

	public void setLastModTime(Date lastModTime) {
		this.lastModTime = DateFormat.dateFormatYMDHMS(lastModTime);
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getModUserId() {
		return modUserId;
	}

	public void setModUserId(Long modUserId) {
		this.modUserId = modUserId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getpCompanyName() {
		return pCompanyName;
	}

	public void setpCompanyName(String pCompanyName) {
		this.pCompanyName = pCompanyName;
	}

	
}
