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
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes.IncomeStatus;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes.IncomeType;
import com.sitv.skyshop.wisdomedu.dto.user.UserInfo;
import com.sitv.skyshop.wisdomedu.dto.user.UserOrderInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioIncomesInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -2756329785484011026L;
	private LiveStudioInfo liveStudio;
	private UserOrderInfo order;
	private UserInfo user;
	private EnumInfo<IncomeType, String> type;
	private BigDecimal incomeAmount;
	private Integer percent;
	@JsonFormat(shape = Shape.NUMBER_FLOAT, pattern = "#.##")
	private BigDecimal totalAmount;
	private EnumInfo<IncomeStatus, String> status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar settleTime;

	private String subjectName;
	private String subjectImg;

	/**
	 *
	 */
	public LiveStudioIncomesInfo() {
	}

	public LiveStudioIncomesInfo(Long id, LiveStudioInfo liveStudio, UserOrderInfo order, UserInfo user, EnumInfo<IncomeType, String> type, BigDecimal incomeAmount,
	                Integer percent, BigDecimal totalAmount, EnumInfo<IncomeStatus, String> status, String subjectName, String subjectImg, Calendar settleTime,
	                Calendar createTime) {
		super(id, createTime, null);
		this.liveStudio = liveStudio;
		this.order = order;
		this.user = user;
		this.type = type;
		this.incomeAmount = incomeAmount;
		this.percent = percent;
		this.totalAmount = totalAmount;
		this.status = status;
		this.settleTime = settleTime;
		this.subjectImg = subjectImg;
		this.subjectName = subjectName;
	}

	public static LiveStudioIncomesInfo create(LiveStudioIncomes c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioIncomesInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), UserOrderInfo.create(c.getOrder()), UserInfo.create(c.getUser()),
		                EnumInfo.valueOf(IncomeType.class, c.getType().getCode()), c.getIncomeAmount(), c.getPercent(), c.getTotalAmount(),
		                EnumInfo.valueOf(IncomeStatus.class, c.getStatus().getCode()), c.getSubjectName(), c.getSubjectImg(), c.getSettleTime(), c.getCreateTime());
	}

	public static List<LiveStudioIncomesInfo> creates(List<LiveStudioIncomes> list) {
		List<LiveStudioIncomesInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioIncomes u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioIncomesInfo.create(u));
		}
		return infos;
	}
}
