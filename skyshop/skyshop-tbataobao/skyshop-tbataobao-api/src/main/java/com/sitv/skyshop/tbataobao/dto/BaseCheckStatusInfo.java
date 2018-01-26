/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseCheckStatusInfo extends FullInfoDto {

	private static final long serialVersionUID = -7645633226267138091L;

	protected EnumInfo<CheckStatus, String> checkStatus;

	protected CheckFailedRemarkInfo remarkInfo;
}
