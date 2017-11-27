package com.sitv.skyshop.user.domain;

import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.domain.DomainObject;

public abstract class User extends DomainObject {
	private String account;
	private String pwd;
	
	private FullAddress fullAddress;
	private String email;
	private String mobile;
	private RegisterType registerType;
	private String logo;
	private UserStatus status;
	private String wxOpenid;
	private String wxUnionid;
	private String weiboid;
	private String qqid;
	private String twCompanyCode;
	private UserLevel level;
	
	protected User() {
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public FullAddress getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(FullAddress fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public RegisterType getRegisterType() {
		return registerType;
	}
	public void setRegisterType(RegisterType registerType) {
		this.registerType = registerType;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getWxUnionid() {
		return wxUnionid;
	}
	public void setWxUnionid(String wxUnionid) {
		this.wxUnionid = wxUnionid;
	}
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	public String getQqid() {
		return qqid;
	}
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}
	public String getTwCompanyCode() {
		return twCompanyCode;
	}
	public void setTwCompanyCode(String twCompanyCode) {
		this.twCompanyCode = twCompanyCode;
	}
	public UserLevel getLevel() {
		return level;
	}
	public void setLevel(UserLevel level) {
		this.level = level;
	}
}
