/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrderItem;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrderItem.SubjectType;
import com.sitv.skyshop.wisdomedu.dto.course.SingleCourseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseInfo;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioCouponInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserOrderItemInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -8702953377806073849L;
	private FullInfoDto subject;
	private EnumInfo<SubjectType, String> subjectType;
	private String subjectName;
	private String subjectImg;
	private Integer qty;
	private BigDecimal price;

	private LiveStudioCouponInfo coupon;
	private String couponName;
	private BigDecimal couponMoney;

	private boolean isGroupBuy;

	private BigDecimal discountPrice;

	private String remark;

	/**
	 *
	 */
	public UserOrderItemInfo() {
	}

	public static UserOrderItemInfo create(UserOrderItem u) {
		if (u == null) {
			return null;
		}
		UserOrderItemInfo orderItemInfo = new UserOrderItemInfo(u.getId(), null, EnumInfo.valueOf(SubjectType.class, u.getSubjectType().getCode()), u.getSubjectName(),
		                u.getSubjectImg(), u.getQty(), u.getPrice(), LiveStudioCouponInfo.create(u.getCoupon()), u.getCouponName(), u.getCouponMoney(), u.isGroupBuy(),
		                u.getDiscountPrice(), u.getRemark(), u.getCreateTime());
		if (u.getSubjectType() == SubjectType.TOPIC) {
			SingleCourseInfo courseInfo = SingleCourseInfo.create((SingleCourse) u.getSubject());
			orderItemInfo.setSubject(courseInfo);
		} else if (u.getSubjectType() == SubjectType.SERIESCOURSE) {
			SeriesCourseInfo seriesCourseInfo = SeriesCourseInfo.create((SeriesCourse) u.getSubject());
			orderItemInfo.setSubject(seriesCourseInfo);
		}
		return orderItemInfo;
	}

	public static List<UserOrderItemInfo> creates(List<UserOrderItem> list) {
		List<UserOrderItemInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (UserOrderItem u : list) {
			if (u == null) {
				continue;
			}
			infos.add(UserOrderItemInfo.create(u));
		}
		return infos;
	}

	public UserOrderItemInfo(Long id, FullInfoDto subject, EnumInfo<SubjectType, String> subjectType, String subjectName, String subjectImg, Integer qty, BigDecimal price,
	                LiveStudioCouponInfo coupon, String couponName, BigDecimal couponMoney, boolean isGroupBuy, BigDecimal discountPrice, String remark, Calendar createTime) {
		super(id, createTime, null);
		this.subject = subject;
		this.subjectType = subjectType;
		this.subjectName = subjectName;
		this.subjectImg = subjectImg;
		this.qty = qty;
		this.price = price;
		this.coupon = coupon;
		this.couponName = couponName;
		this.couponMoney = couponMoney;
		this.isGroupBuy = isGroupBuy;
		this.discountPrice = discountPrice;
		this.remark = remark;
	}
}
