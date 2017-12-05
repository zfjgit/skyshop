/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device.malfunction;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class Malfunction extends DomainObject {

	private MassageChair chair;

	private MalfunctionStatus status;

	private MalfunctionType type;

	public Malfunction(Long id, MassageChair chair, MalfunctionType type, MalfunctionStatus status, String description, Calendar createTime, Calendar updateTime) {
		super(id, null, description);
		this.setChair(chair);
		this.status = status;
		this.type = type;
		setCreateTime(createTime);
		setUpdateTime(updateTime);
	}

	/**
	 * @return the status
	 */
	public MalfunctionStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(MalfunctionStatus status) {
		this.status = status;
	}

	public MalfunctionType getType() {
		return type;
	}

	public void setType(MalfunctionType type) {
		this.type = type;
	}

	public MassageChair getChair() {
		return chair;
	}

	public void setChair(MassageChair chair) {
		this.chair = chair;
	}

	public enum MalfunctionStatus implements BaseEnum<MalfunctionStatus, String> {
		NEW("A", "待处理"), PROCESSED("B", "已处理");

		private String code;
		private String name;

		private MalfunctionStatus(String code, String name) {
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

	public enum MalfunctionType implements BaseEnum<MalfunctionType, String> {
		SIM("A", "SIM卡故障"), GSM("B", "模块故障"), CHAIR("B", "按摩椅故障");

		private String code;
		private String name;

		private MalfunctionType(String code, String name) {
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
