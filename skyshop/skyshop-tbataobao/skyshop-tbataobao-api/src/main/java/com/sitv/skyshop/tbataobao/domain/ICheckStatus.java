/**
 *
 */
package com.sitv.skyshop.tbataobao.domain;

import com.sitv.skyshop.domain.BaseEnum;

import lombok.Getter;

/**
 * @author zfj20 @ 2018年3月24日
 */
public interface ICheckStatus {

	CheckStatus getCheckStatus();

	void setCheckStatus(CheckStatus checkStatus);

	@Getter
	public enum CheckStatus implements BaseEnum<CheckStatus, String> {
		SUCCESSED("1", "审核通过"), FAILED("2", "审核未通过"), UNCHECKED("0", "待审核");

		private String code;
		private String name;

		private CheckStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

	}
}
