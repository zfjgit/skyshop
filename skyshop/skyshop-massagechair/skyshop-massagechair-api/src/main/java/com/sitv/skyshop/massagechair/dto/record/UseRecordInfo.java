/**
 *
 */
package com.sitv.skyshop.massagechair.dto.record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.record.UseRecord;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class UseRecordInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -6483246768896565446L;
	private String from;
	private String response;
	private String imei;
	private String sim;
	private String price;
	private String chair;

	/**
	 *
	 */
	public UseRecordInfo() {
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param createTime
	 * @param from
	 * @param response
	 * @param imei
	 * @param sim
	 * @param price
	 * @param chair
	 */
	public UseRecordInfo(Long id, String name, String description, Calendar createTime, String from, String response, String imei, String sim, String price, String chair) {
		super(id, name, description);
		setCreateTime(createTime);
		this.chair = chair;
		this.from = from;
		this.imei = imei;
		this.price = price;
		this.response = price;
		this.sim = sim;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
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
	 * @return the sim
	 */
	public String getSim() {
		return sim;
	}

	/**
	 * @param sim
	 *            the sim to set
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the chair
	 */
	public String getChair() {
		return chair;
	}

	/**
	 * @param chair
	 *            the chair to set
	 */
	public void setChair(String chair) {
		this.chair = chair;
	}

	/**
	 * @param useRecord
	 * @return
	 */
	public static UseRecordInfo create(UseRecord r) {
		if (r == null) {
			return null;
		}
		return new UseRecordInfo(r.getId(), r.getName(), r.getDescription(), r.getCreateTime(), r.getFrom(), r.getResponse(), r.getImei(), r.getSim(), r.getPrice(), r.getChair());
	}

	public static List<UseRecordInfo> creates(List<UseRecord> rs) {
		List<UseRecordInfo> infos = new ArrayList<>();
		if (rs != null) {
			for (UseRecord useRecord : rs) {
				infos.add(create(useRecord));
			}
		}
		return infos;
	}
}
