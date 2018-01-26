/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.course;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class SeriesCourse extends Course {

	private LiveStudio liveStudio;

	private String title;
	private Integer count;
	private SeriesCourseCategory category;
	private ChargeType chargeType;
	private BigDecimal ticketsMoney;
	private PromotionType promotionType;
	private boolean isDistribution;
	private Integer distributionPercent;

	private String headimg;
	private String introduction;
	private BigDecimal specialOffer;

	private int buyCount;

	@Getter
	public enum ChargeType implements BaseEnum<ChargeType, String> {
		FIXED("1", ""), BYTIME("2", "");

		private String code;
		private String name;

		private ChargeType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@Getter
	public enum PromotionType implements BaseEnum<PromotionType, String> {
		BARGAIN("1", ""), GROUPBUY("2", ""), NONE("3", "");

		private String code;
		private String name;

		private PromotionType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
