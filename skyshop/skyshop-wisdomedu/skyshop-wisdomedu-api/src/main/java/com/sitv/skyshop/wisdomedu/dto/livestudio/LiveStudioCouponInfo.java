/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioCoupon;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioCouponInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -449097195189895057L;
	private LiveStudioInfo liveStudio;
	private Integer numberLimit;
	private BigDecimal money;
	private Calendar endDate;
	private BigDecimal minConsumption;
	private String introduction;
	private String link;
	private boolean isShow;

	/**
	 *
	 */
	public LiveStudioCouponInfo() {
	}

	public LiveStudioCouponInfo(Long id, String name, LiveStudioInfo liveStudio, Integer numberLimit, BigDecimal money, Calendar endDate, BigDecimal minConsumption,
	                String introduction, String link, boolean isShow) {
		super(id, name);
		this.liveStudio = liveStudio;
		this.numberLimit = numberLimit;
		this.money = money;
		this.endDate = endDate;
		this.minConsumption = minConsumption;
		this.introduction = introduction;
		this.link = link;
		this.isShow = isShow;
	}

	public static LiveStudioCouponInfo create(LiveStudioCoupon c) {
		if (c == null) {
			return null;
		}
		return new LiveStudioCouponInfo(c.getId(), c.getName(), LiveStudioInfo.create(c.getLiveStudio()), c.getNumberLimit(), c.getMoney(), c.getEndDate(), c.getMinConsumption(),
		                c.getIntroduction(), c.getLink(), c.isShow());
	}

	public static List<LiveStudioCouponInfo> creates(List<LiveStudioCoupon> list) {
		List<LiveStudioCouponInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioCoupon u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioCouponInfo.create(u));
		}
		return infos;
	}
}
