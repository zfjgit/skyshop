/**
 *
 */
package com.sitv.skyshop.massagechair.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2018年1月8日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ChairOrderPartitionReportVO extends OrderPartitionReportVO {

	private static final long serialVersionUID = 6007701146867827974L;

	private String chairName;
	private Long chairId;
}
