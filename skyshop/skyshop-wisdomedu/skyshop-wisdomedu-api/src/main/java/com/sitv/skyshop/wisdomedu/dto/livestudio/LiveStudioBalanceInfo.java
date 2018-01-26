/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sitv.skyshop.common.utils.converters.CurrencyFormat;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioBalance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 */
@Getter
@Setter
@ToString
public class LiveStudioBalanceInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -6362902204567104267L;

	private LiveStudioInfo liveStudio;

	private BigDecimal courseIncome;
	private BigDecimal seriesCourseIncome;
	private BigDecimal vipIncome;
	private BigDecimal punchIncome;
	private BigDecimal unsettledAmount;
	private BigDecimal availableAmount;
	private BigDecimal frozenAmount;
	@JsonFormat(shape = Shape.NUMBER_FLOAT, pattern = "#.##")
	private BigDecimal withdrawnAmount;
	@JsonSerialize(using = CurrencyFormat.class)
	@JsonFormat(pattern = "#.##")
	private BigDecimal totalAmount;

	public LiveStudioBalanceInfo() {
	}

	public LiveStudioBalanceInfo(Long id, LiveStudioInfo liveStudio, BigDecimal courseIncome, BigDecimal seriesCourseIncome, BigDecimal vipIncome, BigDecimal punchIncome,
	                BigDecimal unsettledAmount, BigDecimal availableAmount, BigDecimal frozenAmount, BigDecimal withdrawnAmount, BigDecimal totalAmount, Calendar createTime) {
		super(id, createTime, null);
		this.liveStudio = liveStudio;
		this.courseIncome = courseIncome;
		this.seriesCourseIncome = seriesCourseIncome;
		this.vipIncome = vipIncome;
		this.punchIncome = punchIncome;
		this.unsettledAmount = unsettledAmount;
		this.availableAmount = availableAmount;
		this.frozenAmount = frozenAmount;
		this.withdrawnAmount = withdrawnAmount;
		this.totalAmount = totalAmount;
	}

	public static LiveStudioBalanceInfo create(LiveStudioBalance c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioBalanceInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getCourseIncome(), c.getSeriesCourseIncome(), c.getVipIncome(), c.getPunchIncome(),
		                c.getUnsettledAmount(), c.getAvailableAmount(), c.getFrozenAmount(), c.getWithdrawnAmount(), c.getTotalAmount(), c.getCreateTime());
	}

	public static List<LiveStudioBalanceInfo> creates(List<LiveStudioBalance> list) {
		List<LiveStudioBalanceInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioBalance u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioBalanceInfo.create(u));
		}
		return infos;
	}
}
