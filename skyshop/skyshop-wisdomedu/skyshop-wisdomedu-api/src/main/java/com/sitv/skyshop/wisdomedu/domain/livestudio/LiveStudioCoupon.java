/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioCoupon extends DomainObject {

	private LiveStudio liveStudio;
	private Integer numberLimit;
	private BigDecimal money;
	private Calendar endDate;
	private BigDecimal minConsumption;
	private String introduction;
	private String link;
	private boolean isShow;
}
