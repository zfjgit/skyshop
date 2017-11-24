package com.sitv.skyshop.user.domain.member;

import java.util.Calendar;

import com.sitv.skyshop.user.domain.Gender;
import com.sitv.skyshop.user.domain.User;

public class Member extends User {

	private Gender gender;
	private Calendar birthday;
	private String signature;
	private Long recommonderId;
	
	protected Member() {
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Calendar getBirthday() {
		return birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Long getRecommonderId() {
		return recommonderId;
	}
	public void setRecommonderId(Long recommonderId) {
		this.recommonderId = recommonderId;
	}
}
