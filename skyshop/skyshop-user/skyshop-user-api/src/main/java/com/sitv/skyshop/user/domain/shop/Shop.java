package com.sitv.skyshop.user.domain.shop;

import com.sitv.skyshop.user.domain.User;

public class Shop extends User {

	private String site;
	private String website;
	private String banner1;
	private String banner2;
	private String banner3;
	private OperationType operationType;
	
	protected Shop() {
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBanner1() {
		return banner1;
	}
	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}
	public String getBanner2() {
		return banner2;
	}
	public void setBanner2(String banner2) {
		this.banner2 = banner2;
	}
	public String getBanner3() {
		return banner3;
	}
	public void setBanner3(String banner3) {
		this.banner3 = banner3;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
}
