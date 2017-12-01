/**
 *
 */
package com.sitv.skyshop.massagechair.domain.record;

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

	protected UseRecord() {
	}

	public UseRecord(Long id, String from, String response, UseRecordType type, String imei, String sim, String price, String minutes, String chair, String openid, String nickName,
	                String addr) {
		super(id, "");
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
