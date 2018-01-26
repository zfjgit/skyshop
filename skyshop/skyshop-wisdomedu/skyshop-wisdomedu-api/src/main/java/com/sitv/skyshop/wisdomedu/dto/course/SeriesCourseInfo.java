/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse.ChargeType;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse.PromotionType;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class SeriesCourseInfo extends CourseInfo {

	private static final long serialVersionUID = 9073702179525936662L;

	private LiveStudioInfo liveStudio;

	private String title;
	private Integer count;
	private SeriesCourseCategoryInfo category;
	private EnumInfo<ChargeType, String> chargeType;
	private BigDecimal ticketsMoney;
	private EnumInfo<PromotionType, String> promotionType;
	private boolean isDistribution;
	private Integer distributionPercent;

	private String headimg;
	private String introduction;
	private BigDecimal specialOffer;
	private int buyCount;

	public SeriesCourseInfo() {
	}

	public SeriesCourseInfo(Long id, LiveStudioInfo liveStudio, String title, Integer count, SeriesCourseCategoryInfo category, EnumInfo<ChargeType, String> chargeType,
	                BigDecimal ticketsMoney, EnumInfo<PromotionType, String> promotionType, boolean isDistribution, Integer distributionPercent, String headimg,
	                String introduction, BigDecimal specialOffer, int buyCount, Calendar createTime) {
		super(id, createTime);
		this.liveStudio = liveStudio;
		this.title = title;
		this.count = count;
		this.category = category;
		this.chargeType = chargeType;
		this.ticketsMoney = ticketsMoney;
		this.promotionType = promotionType;
		this.isDistribution = isDistribution;
		this.distributionPercent = distributionPercent;
		this.headimg = headimg;
		this.introduction = introduction;
		this.specialOffer = specialOffer;
		this.buyCount = buyCount;
	}

	public static List<SeriesCourseInfo> creates(List<SeriesCourse> list) {
		List<SeriesCourseInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (SeriesCourse u : list) {
			if (u == null) {
				continue;
			}
			infos.add(SeriesCourseInfo.create(u));
		}
		return infos;
	}

	public static SeriesCourseInfo create(SeriesCourse c) {
		if (c == null) {
			return null;
		}
		EnumInfo<PromotionType, String> promotionType = null;
		if (c.getPromotionType() != null) {
			promotionType = EnumInfo.valueOf(PromotionType.class, c.getPromotionType().getCode());
		}
		return new SeriesCourseInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getTitle(), c.getCount(), SeriesCourseCategoryInfo.create(c.getCategory()),
		                EnumInfo.valueOf(ChargeType.class, c.getChargeType().getCode()), c.getTicketsMoney(), promotionType, c.isDistribution(), c.getDistributionPercent(),
		                c.getHeadimg(), c.getIntroduction(), c.getSpecialOffer(), c.getBuyCount(), c.getCreateTime());
	}

}
