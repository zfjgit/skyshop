/**
 *
 */
package com.sitv.skyshop.massagechair.dto.record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.record.UseRecord;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UseRecordInfo extends FullInfoDto {

	private static final long serialVersionUID = -6483246768896565446L;

	private Long orderId;
	private String resultCode;
	private String from;
	private String response;
	private String imei;
	private String sim;
	private String price;
	private String minutes;
	private String chair;

	private String openid;

	private String nickName;

	private String addr;

	private String url;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

	private EnumInfo<UseRecordType, String> type;

	public UseRecordInfo() {
	}

	public UseRecordInfo(Long id, Long orderId, String resultCode, EnumInfo<UseRecordType, String> type, String from, String response, String imei, String sim, String price,
	                String minutes, String chair, String openid, String nickName, String addr, String url, Calendar createTime) {
		super(id, "");
		this.orderId = orderId;
		this.resultCode = resultCode;
		this.from = from;
		this.response = response;
		this.imei = imei;
		this.sim = sim;
		this.price = price;
		this.minutes = minutes;
		this.chair = chair;
		this.openid = openid;
		this.nickName = nickName;
		this.addr = addr;
		this.type = type;
		this.url = url;
		setCreateTime(createTime);
	}

	public static UseRecordInfo create(UseRecord r) {
		if (r == null) {
			return null;
		}
		return new UseRecordInfo(r.getId(), r.getOrderId(), r.getResultCode(), new EnumInfo<>(r.getType()), r.getFrom(), r.getResponse(), r.getImei(), r.getSim(), r.getPrice(),
		                r.getMinutes(), r.getChair(), r.getOpenid(), r.getNickName(), r.getAddr(), r.getUrl(), r.getCreateTime());
	}

	public static List<UseRecordInfo> creates(List<UseRecord> rs) {
		List<UseRecordInfo> infos = new ArrayList<>();
		if (rs != null) {
			for (UseRecord useRecord : rs) {
				infos.add(create(useRecord));
			}
		}
		return infos;
	}

}
