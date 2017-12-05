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
	private String minutes;
	private String chair;

	private String openid;

	private String nickName;

	private String addr;

	public UseRecordInfo() {
	}

	public UseRecordInfo(Long id, String name, String from, String response, String imei, String sim, String price, String minutes, String chair, String openid, String nickName,
	                String addr, Calendar createTime) {
		super(id, name);
		this.from = from;
		this.response = response;
		this.imei = imei;
		this.sim = sim;
		this.price = price;
		this.minutes = minutes;
		this.chair = chair;
		this.openid = openid;
		this.nickName = nickName;
		this.addr = addr;
		setCreateTime(createTime);
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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
		return new UseRecordInfo(r.getId(), r.getName(), r.getFrom(), r.getResponse(), r.getImei(), r.getSim(), r.getPrice(), r.getMinutes(), r.getChair(), r.getOpenid(),
		                r.getNickName(), r.getAddr(), r.getCreateTime());
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
