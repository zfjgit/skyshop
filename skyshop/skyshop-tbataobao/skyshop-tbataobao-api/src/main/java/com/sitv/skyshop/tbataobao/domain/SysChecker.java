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
public class SysChecker extends DomainObject {

	private String account;
	private String pwd;
}
