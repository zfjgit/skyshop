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
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder.OrderStatus;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder.PayType;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class UserOrderInfo extends FullInfoDto {

	private static final long serialVersionUID = 9025880191778050595L;

	private LiveStudioInfo liveStudio;
	private UserInfo user;
	private EnumInfo<OrderStatus, String> status;
	private EnumInfo<PayType, String> payType;
	private String payTransactionId;
	private BigDecimal totalAmount;
	private BigDecimal paidAmount;
	private BigDecimal discountAmount;
	private String remark;
	private Calendar payTime;

	public UserOrderInfo() {

	}

	public UserOrderInfo(Long id, String code, LiveStudioInfo liveStudio, UserInfo user, EnumInfo<OrderStatus, String> status, EnumInfo<PayType, String> payType,
	                String payTransactionId, BigDecimal totalAmount, BigDecimal paidAmount, BigDecimal discountAmount, String remark, Calendar payTime, Calendar createTime) {
		super(id, code);
		this.liveStudio = liveStudio;
		this.user = user;
		this.status = status;
		this.payType = payType;
		this.payTransactionId = payTransactionId;
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.discountAmount = discountAmount;
		this.remark = remark;
		this.payTime = payTime;

		setCreateTime(createTime);
	}

	public static UserOrderInfo create(UserOrder o) {
		if (o == null) {
			return null;
		}
		return new UserOrderInfo(o.getId(), o.getCode(), LiveStudioInfo.create(o.getLiveStudio()), UserInfo.create(o.getUser()),
		                EnumInfo.valueOf(OrderStatus.class, o.getStatus().getCode()), EnumInfo.valueOf(PayType.class, o.getPayType().getCode()), o.getPayTransactionId(),
		                o.getTotalAmount(), o.getPaidAmount(), o.getDiscountAmount(), o.getRemark(), o.getPayTime(), o.getCreateTime());
	}

	public static List<UserOrderInfo> creates(List<UserOrder> list) {
		List<UserOrderInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (UserOrder userOrder : list) {
			if (userOrder == null) {
				continue;
			}
			infos.add(UserOrderInfo.create(userOrder));
		}
		return infos;
	}
}
