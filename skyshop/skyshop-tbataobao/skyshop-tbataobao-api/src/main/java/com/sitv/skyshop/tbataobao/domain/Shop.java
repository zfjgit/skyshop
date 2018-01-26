/**
 *
 */
package com.sitv.skyshop.tbataobao.domain;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Getter
@Setter
public class Shop extends DomainObject implements ICheckStatus {

	private CheckStatus checkStatus;

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(CheckStatus checkStatus) {
		this.checkStatus = checkStatus;
	}

}
