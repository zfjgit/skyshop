/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class GSMModule extends Device {

	private String imei;

	private SIMCard simCard;

	private String module;

	private GSMModuleStatus status;

	protected GSMModule() {
	}

	public GSMModule(Long id, String imei, String module, GSMModuleStatus status, SIMCard simCard, String description, Calendar createTime, Calendar updateTime,
	                DeleteStatus deleteStatus) {
		super(id, null, description, deleteStatus);
		this.imei = imei;
		this.module = module;
		this.simCard = simCard;
		this.status = status;
		setCreateTime(createTime);
		setUpdateTime(updateTime);
	}

	public enum GSMModuleStatus implements BaseEnum<GSMModuleStatus, String> {
		USING("A", "使用中"), UNUSED("B", "未使用");

		private String code;
		private String name;

		private GSMModuleStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

}
