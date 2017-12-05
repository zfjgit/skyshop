/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.massagechair.dto.user.UserOverviewInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月7日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyOverviewInfo extends UserOverviewInfo {

	private static final long serialVersionUID = 2241815798814958574L;

	public AgencyOverviewInfo(int chairCount, int addrCount, int agencyCount, int priceCount, int orderCount, int orderPartitionCount, BigDecimal total) {
		super(chairCount, addrCount, agencyCount, priceCount, orderCount, orderPartitionCount, total);
	}

}
