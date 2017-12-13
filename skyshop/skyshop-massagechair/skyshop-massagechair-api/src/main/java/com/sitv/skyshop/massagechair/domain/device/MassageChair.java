/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.price.Price;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MassageChair extends Device {

	private GSMModule gsmModule;

	private InstallationAddress installationAddress;

	private List<Price> prices;

	private String brand;

	private ChairStatus status;

	private Agency agency;

	private String qrcode;

	protected MassageChair() {
	}

	public MassageChair(Long id, String name, String description, String brand, ChairStatus status, GSMModule gsmModule, InstallationAddress installationAddress, Agency agency,
	                DeleteStatus deleteStatus, String qrcode) {
		super(id, name, description, deleteStatus);
		this.agency = agency;
		this.brand = brand;
		this.qrcode = qrcode;
		this.status = status;
		this.gsmModule = gsmModule;
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		this.installationAddress = installationAddress;
	}

	public enum ChairStatus implements BaseEnum<ChairStatus, String> {
		NOTINSTALLED("A", "未投放"), NORMAL("B", "正常未使用"), WORKING("C", "正常使用中"), FAULT("D", "故障");

		private String code;
		private String name;

		private ChairStatus(String code, String name) {
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

	public enum WorkStatus {
		WORKING("WORKING", "工作中"), IDLE("IDLE", "空闲");

		private String code;
		private String name;

		private WorkStatus(String code, String name) {
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
