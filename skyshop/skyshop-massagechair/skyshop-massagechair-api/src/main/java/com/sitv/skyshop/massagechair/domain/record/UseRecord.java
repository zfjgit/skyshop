/**
 *
 */
package com.sitv.skyshop.massagechair.domain.record;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UseRecord extends DomainObject {

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
	private UseRecordType type;
	private String url;

	private Calendar chairStartTime;

	protected UseRecord() {
	}

	public UseRecord(Long id, Long orderId, String resultCode, String from, String response, UseRecordType type, String imei, String sim, String price, String minutes,
	                String chair, String openid, String nickName, String addr, String url, Calendar chairStartTime) {
		super(id, type.getName());
		this.orderId = orderId;
		this.resultCode = resultCode;
		this.type = type;
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
		this.url = url;
		this.chairStartTime = chairStartTime;
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
	}

	public enum UseRecordType implements BaseEnum<UseRecordType, String> {
		CHECK("A", "设备检测"), OPEN("B", "开机"), CLOSE("C", "关机"), URL("D", "设置接口地址");

		private String code;
		private String name;

		private UseRecordType(String code, String name) {
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
