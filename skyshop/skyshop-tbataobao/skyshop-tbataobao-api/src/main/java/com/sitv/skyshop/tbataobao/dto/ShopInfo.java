/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Getter
@Setter
@JsonIgnoreProperties({ "loginPwd" })
public class ShopInfo extends BaseCheckStatusInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 6138854709610755774L;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Calendar endDate;
}
