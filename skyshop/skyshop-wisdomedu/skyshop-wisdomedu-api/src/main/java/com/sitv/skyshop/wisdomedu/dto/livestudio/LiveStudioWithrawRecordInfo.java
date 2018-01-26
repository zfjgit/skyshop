/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord.WithrawStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioWithrawRecordInfo extends FullInfoDto {

	private static final long serialVersionUID = 5289047411901692271L;
	private LiveStudioInfo liveStudio;
	private String bankUser;
	private String bankName;
	private String bankNum;
	private BigDecimal amount;
	private BigDecimal beforeAmount;
	private BigDecimal afterAmount;
	private EnumInfo<WithrawStatus, String> status;

	public LiveStudioWithrawRecordInfo() {
	}

	public LiveStudioWithrawRecordInfo(Long id, LiveStudioInfo liveStudio, String bankUser, String bankName, String bankNum, BigDecimal amount, BigDecimal beforeAmount,
	                BigDecimal afterAmount, EnumInfo<WithrawStatus, String> status, Calendar createTime, String description) {
		super(id, description, createTime, null);
		this.liveStudio = liveStudio;
		this.bankUser = bankUser;
		this.bankName = bankName;
		this.bankNum = bankNum;
		this.amount = amount;
		this.beforeAmount = beforeAmount;
		this.afterAmount = afterAmount;
		this.status = status;
	}

	public static LiveStudioWithrawRecordInfo create(LiveStudioWithrawRecord c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioWithrawRecordInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getBankUser(), c.getBankName(), c.getBankNum(), c.getAmount(),
		                c.getBeforeAmount(), c.getAfterAmount(), EnumInfo.valueOf(WithrawStatus.class, c.getStatus().getCode()), c.getCreateTime(), c.getDescription());
	}

	public static List<LiveStudioWithrawRecordInfo> creates(List<LiveStudioWithrawRecord> list) {
		List<LiveStudioWithrawRecordInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioWithrawRecord u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioWithrawRecordInfo.create(u));
		}
		return infos;
	}
}
