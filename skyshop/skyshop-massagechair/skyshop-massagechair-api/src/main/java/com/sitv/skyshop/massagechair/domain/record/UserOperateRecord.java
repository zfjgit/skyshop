/**
 *
 */
package com.sitv.skyshop.massagechair.domain.record;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserOperateRecord extends DomainObject {
	private Agency agency;
	private String account;

	private String ip;

	private OperateType type;

	public UserOperateRecord() {
	}

	public UserOperateRecord(Agency agency, String account, String ip, OperateType type, String description, Calendar createTime) {
		super();
		this.agency = agency;
		this.account = account;
		this.ip = ip;
		this.type = type;
		setDescription(description);
		setCreateTime(createTime);
	}

	public enum OperateType implements BaseEnum<OperateType, String> {
		SYSTEM("A", "系统"), AGENCY("B", "代理商"), DEVICE("C", "设备"), ADDRESS("D", "地址"), PRICE("E", "价格");

		private String code;
		private String name;

		private OperateType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

}
