/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ProductInfo extends BaseCheckStatusInfo {

	private static final long serialVersionUID = 7047048134008251618L;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Calendar startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Calendar endDate;
	
}
