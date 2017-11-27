/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sitv.skyshop.massagechair.domain.device.Device.DeviceStatus;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MassageChairInfo extends DeviceInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -5318115230379761101L;

	@NotNull
	private GSMModuleInfo gsmModule;

	@NotNull
	private InstallationAddressInfo installationAddress;

	private List<PriceInfo> prices;

	@NotBlank
	private String priceIds;

	private boolean isPromotionPrice;

	// 品牌、厂商
	private String brand;

	/**
	 * @param id
	 * @param name
	 * @param brand
	 * @param description
	 * @param createTime
	 * @param updateTime
	 * @param promotionPrice
	 * @param status
	 * @param installationAddressInfo
	 * @param gsmModuleInfo
	 */
	public MassageChairInfo(Long id, String name, String brand, String description, Calendar createTime, Calendar updateTime, boolean isPromotionPrice, DeviceStatus status,
	                InstallationAddressInfo installationAddressInfo, GSMModuleInfo gsmModuleInfo, List<PriceInfo> prices) {
		super(id, name, description, status, createTime, updateTime);
		this.gsmModule = gsmModuleInfo;
		this.brand = brand;
		this.installationAddress = installationAddressInfo;
		this.isPromotionPrice = isPromotionPrice;
		this.prices = prices;
	}

	/**
	 * @return the gsmModule
	 */
	public GSMModuleInfo getGsmModule() {
		return gsmModule;
	}

	/**
	 * @param gsmModule
	 *            the gsmModule to set
	 */
	public void setGsmModule(GSMModuleInfo gsmModule) {
		this.gsmModule = gsmModule;
	}

	/**
	 * @return the prices
	 */
	public List<PriceInfo> getPrices() {
		return prices;
	}

	/**
	 * @param prices
	 *            the prices to set
	 */
	public void setPrices(List<PriceInfo> prices) {
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

	public InstallationAddressInfo getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(InstallationAddressInfo installationAddress) {
		this.installationAddress = installationAddress;
	}

	public static MassageChairInfo create(MassageChair massageChair) {
		if (massageChair == null) {
			return null;
		}

		InstallationAddressInfo installationAddressInfo = InstallationAddressInfo.create(massageChair.getInstallationAddress());
		GSMModuleInfo gsmModuleInfo = GSMModuleInfo.create(massageChair.getGsmModule());

		List<PriceInfo> prices = PriceInfo.creates(massageChair.getPrices());

		return new MassageChairInfo(massageChair.getId(), massageChair.getName(), massageChair.getBrand(), massageChair.getDescription(), massageChair.getCreateTime(),
		                massageChair.getUpdateTime(), massageChair.isPromotionPrice(), massageChair.getStatus(), installationAddressInfo, gsmModuleInfo, prices);
	}

	public static List<MassageChairInfo> creates(List<MassageChair> list) {
		List<MassageChairInfo> massageChairInfos = new ArrayList<>();
		if (list != null) {
			for (MassageChair massageChair : list) {
				massageChairInfos.add(create(massageChair));
			}
		}
		return massageChairInfos;
	}

	public String getPriceIds() {
		return priceIds;
	}

	public void setPriceIds(String priceIds) {
		this.priceIds = priceIds;
	}

}
