/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.IDeleteStatus;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class GSMModule extends Device implements IDeleteStatus {

	private String imei;

	private SIMCard simCard;

	// 型号
	private String module;

	private GSMModuleStatus status;

	private DeleteStatus deleteStatus;

	public GSMModule(Long id, String imei, String module, GSMModuleStatus status, SIMCard simCard, String description, Calendar createTime, Calendar updateTime) {
		super(id, null, description);
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

	public GSMModuleStatus getStatus() {
		return status;
	}

	public void setStatus(GSMModuleStatus status) {
		this.status = status;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

}
