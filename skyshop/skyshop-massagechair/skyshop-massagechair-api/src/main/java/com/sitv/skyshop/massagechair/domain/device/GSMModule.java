/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class GSMModule extends Device {

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public GSMModule(Long id, String name, String description) {
		super(id, name, description);
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param imei
	 * @param module
	 * @param simCard
	 */
	public GSMModule(Long id, String name, String description, String imei, String module, SIMCard simCard) {
		this(id, name, description);
		this.imei = imei;
		this.module = module;
		this.simCard = simCard;
	}

	private String imei;

	private SIMCard simCard;

	// 型号
	private String module;

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the simCard
	 */
	public SIMCard getSimCard() {
		return simCard;
	}

	/**
	 * @param simCard
	 *            the simCard to set
	 */
	public void setSimCard(SIMCard simCard) {
		this.simCard = simCard;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

}
