/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.List;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.IDeleteStatus;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.price.Price;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MassageChair extends Device implements IDeleteStatus {

	private GSMModule gsmModule;

	private InstallationAddress installationAddress;

	private List<Price> prices;

	// 品牌、厂商
	private String brand;

	private ChairStatus status;

	private Agency agency;

	private DeleteStatus deleteStatus;

	public enum ChairStatus implements BaseEnum<ChairStatus, String> {
		OFFLINE("A", "未投放"), ONLINE("B", "正常未使用"), WORKING("C", "正常使用中"), FAULT("D", "故障");

		private String code;
		private String name;

		private ChairStatus(String code, String name) {
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

	public MassageChair(Long id, String name, String description, String brand, ChairStatus status, GSMModule gsmModule, InstallationAddress installationAddress, Agency agency) {
		super(id, name, description);
		this.agency = agency;
		this.brand = brand;
		this.gsmModule = gsmModule;
		this.installationAddress = installationAddress;
	}

	/**
	 * @return the gsmModule
	 */
	public GSMModule getGsmModule() {
		return gsmModule;
	}

	/**
	 * @param gsmModule
	 *            the gsmModule to set
	 */
	public void setGsmModule(GSMModule gsmModule) {
		this.gsmModule = gsmModule;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the prices
	 */
	public List<Price> getPrices() {
		return prices;
	}

	/**
	 * @param prices
	 *            the prices to set
	 */
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public InstallationAddress getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(InstallationAddress installationAddress) {
		this.installationAddress = installationAddress;
	}

	public ChairStatus getStatus() {
		return status;
	}

	public void setStatus(ChairStatus status) {
		this.status = status;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
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
