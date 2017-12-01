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
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord;
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord.OperateType;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserOperateRecordInfo extends FullInfoDto {
	private static final long serialVersionUID = 5861723646119935867L;

	private String ip;
	private String account;
	private AgencyInfo agency;
	private EnumInfo<OperateType, String> type;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

	public UserOperateRecordInfo() {
	}

	public UserOperateRecordInfo(AgencyInfo agency, String account, String ip, EnumInfo<OperateType, String> type, String desc, Calendar createTime) {
		super();
		this.agency = agency;
		this.account = account;
		this.ip = ip;
		this.type = type;
		setDescription(desc);
		setCreateTime(createTime);
	}

	public static UserOperateRecordInfo create(UserOperateRecord record) {
		if (record == null) {
			return null;
		}
		AgencyInfo agency = AgencyInfo.create(record.getAgency());
		return new UserOperateRecordInfo(agency, record.getAccount(), record.getIp(), new EnumInfo<>(record.getType()), record.getDescription(), record.getCreateTime());
	}

	public static List<UserOperateRecordInfo> creates(List<UserOperateRecord> records) {
		List<UserOperateRecordInfo> infos = new ArrayList<>();
		if (records == null) {
			return infos;
		}
		for (UserOperateRecord record : records) {
			infos.add(create(record));
		}
		return infos;
	}
}
