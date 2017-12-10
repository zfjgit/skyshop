/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月7日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyOverviewInfo extends FullInfoDto {

	private static final long serialVersionUID = 2241815798814958574L;

	private int chairCount;
	private int addrCount;
	private int agencyCount;
	private int priceCount;
	private int orderCount;
	private int orderPartitionCount;
	private BigDecimal total;

	public AgencyOverviewInfo(int chairCount, int addrCount, int agencyCount, int priceCount, int orderCount, int orderPartitionCount, BigDecimal total) {
		super();
		this.chairCount = chairCount;
		this.addrCount = addrCount;
		this.agencyCount = agencyCount;
		this.priceCount = priceCount;
		this.orderCount = orderCount;
		this.orderPartitionCount = orderPartitionCount;
		this.total = total;
	}

}
