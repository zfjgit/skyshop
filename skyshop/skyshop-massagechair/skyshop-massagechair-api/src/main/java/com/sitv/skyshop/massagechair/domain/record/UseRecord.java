/**
 *
 */
package com.sitv.skyshop.massagechair.domain.record;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class UseRecord extends DomainObject {

	private String from;
	private String response;
	private String imei;
	private String sim;
	private String price;
	private String chair;

	/**
	 *
	 */
	public UseRecord() {
	}

	/**
	 * @param name
	 * @param description
	 * @param from
	 * @param chair
	 * @param imei
	 * @param sim
	 * @param response
	 * @param price
	 */
	public UseRecord(String name, String description, String from, String chair, String imei, String sim, String response, String price) {
		super(null, name, description);
		this.from = from;
		this.response = response;
		this.imei = imei;
		this.sim = sim;
		this.price = price;
		this.chair = chair;
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

}
