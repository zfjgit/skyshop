/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.tbataobao.domain.SysChecker;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Getter
@Setter
@JsonIgnoreProperties("pwd")
@ToString(callSuper = true)
public class SysCheckerInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -9067851786591039090L;
	private String account;
	private String pwd;

	private String token;
	
	private String verifycode;

	/**
	 *
	 */
	public SysCheckerInfo() {
	}

	/**
	 * @param id
	 * @param account
	 * @param pwd
	 */
	public SysCheckerInfo(Long id, String account, String pwd) {
		super(id, account);
		this.account = account;
		this.pwd = pwd;
	}

	public static SysCheckerInfo create(SysChecker sysChecker) {
		if (sysChecker == null) {
			return null;
		}
		return new SysCheckerInfo(sysChecker.getId(), sysChecker.getAccount(), sysChecker.getPwd());
	}
}
