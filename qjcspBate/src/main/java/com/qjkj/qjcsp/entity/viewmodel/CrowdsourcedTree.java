package com.qjkj.qjcsp.entity.viewmodel;

public class CrowdsourcedTree {
	private String universityid;
	private String universityname;
	private String zonename;
	private Double longitude;
	private Double latitude;
	private String spellall;
	private String spellshort;
	private long CityCode;
	private String cityname;
	private long provCode;
	private String province;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	public long getProvCode() {
		return provCode;
	}

	public void setProvCode(long provCode) {
		this.provCode = provCode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}



	public String getUniversityid() {
		return universityid;
	}

	public void setUniversityid(String universityid) {
		this.universityid = universityid;
	}


	public String getUniversityname() {
		return universityname;
	}

	public void setUniversityname(String universityname) {
		this.universityname = universityname;
	}

	public String getZonename() {
		return zonename;
	}

	public void setZonename(String zonename) {
		this.zonename = zonename;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getSpellall() {
		return spellall;
	}

	public void setSpellall(String spellall) {
		this.spellall = spellall;
	}

	public String getSpellshort() {
		return spellshort;
	}

	public void setSpellshort(String spellshort) {
		this.spellshort = spellshort;
	}

	public long getCityCode() {
		return CityCode;
	}

	public void setCityCode(long cityCode) {
		this.CityCode = cityCode;
	}

	
}
