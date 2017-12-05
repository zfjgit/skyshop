/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.common.domain.Withraw.WithrawStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class WithrawInfo extends FullInfoDto {

	private static final long serialVersionUID = -4022477063307462613L;

	private BigDecimal money;
	private EnumInfo<WithrawStatus, String> status;
	private String bank;
	private String account;
	private String accountName;

	public WithrawInfo(Long id, BigDecimal money, EnumInfo<WithrawStatus, String> status, String bank, String account, String accountName, String checkCode, Calendar createTime,
	                Calendar updateTime) {
		super(id, createTime, updateTime);
		this.money = money;
		this.status = status;
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
		setCheckCode(checkCode);
	}

}
