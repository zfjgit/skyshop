/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.List;

import com.sitv.skyshop.massagechair.domain.price.Price;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MassageChair extends Device {

	private GSMModule gsmModule;

	private InstallationAddress installationAddress;

	private List<Price> prices;

	private boolean isPromotionPrice;

	// 品牌、厂商
	private String brand;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param brand
	 * @param promotionPrice
	 * @param status
	 * @param gsmModule
	 * @param installationAddress
	 * @param instance
	 */
	public MassageChair(Long id, String name, String description, String brand, boolean promotionPrice, String status, GSMModule gsmModule,
	                InstallationAddress installationAddress) {
		super(id, name, description);
		this.brand = brand;
		this.gsmModule = gsmModule;
		this.installationAddress = installationAddress;
		this.isPromotionPrice = promotionPrice;
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

	/**
	 * @return the isPromotionPrice
	 */
	public boolean isPromotionPrice() {
		return isPromotionPrice;
	}

	/**
	 * @param isPromotionPrice
	 *            the isPromotionPrice to set
	 */
	public void setPromotionPrice(boolean isPromotionPrice) {
		this.isPromotionPrice = isPromotionPrice;
	}

	public InstallationAddress getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(InstallationAddress installationAddress) {
		this.installationAddress = installationAddress;
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
