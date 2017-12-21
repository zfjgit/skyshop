/**
 *
 */
package com.sitv.skyshop.massagechair.report;

import java.math.BigDecimal;

import com.sitv.skyshop.report.ReportVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2018年1月8日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OrderPartitionReportVO extends ReportVO {

	private static final long serialVersionUID = 1829698741204169330L;

	private int percentage;
	private BigDecimal money;
	private BigDecimal totalMoney;
	private String agencyName;
	private Long agencyId;
	private Long orderId;

	private int orderCount;
}
