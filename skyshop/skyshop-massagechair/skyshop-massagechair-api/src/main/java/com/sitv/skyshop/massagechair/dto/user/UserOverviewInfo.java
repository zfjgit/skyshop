/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

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
public abstract class UserOverviewInfo extends FullInfoDto {
	private static final long serialVersionUID = -1441720076824197812L;

	private int chairCount;
	private int addrCount;
	private int agencyCount;
	private int priceCount;
	private int orderCount;
	private int orderPartitionCount;
	private BigDecimal total;

	public UserOverviewInfo(int chairCount, int addrCount, int agencyCount, int priceCount, int orderCount, int orderPartitionCount, BigDecimal total) {
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
