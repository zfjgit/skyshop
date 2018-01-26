/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 */
@Getter
@Setter
@ToString
public class LiveStudioBalance extends DomainObject {

	private LiveStudio liveStudio;

	private BigDecimal courseIncome;
	private BigDecimal seriesCourseIncome;
	private BigDecimal vipIncome;
	private BigDecimal punchIncome;
	private BigDecimal unsettledAmount;
	private BigDecimal availableAmount;
	private BigDecimal frozenAmount;
	private BigDecimal withdrawnAmount;
	private BigDecimal totalAmount;

}
