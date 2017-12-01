/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.domain.IDeleteStatus;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstallationAddress extends FullAddress implements IDeleteStatus {

	private String contact;
	private String contactNumber;

	private String location;

	private Agency agency;

	private DeleteStatus deleteStatus;

	protected InstallationAddress() {
	}

	public InstallationAddress(Long id, Agency agency, Address province, Address city, Address district, String detailAddress, String description, String location, String contact,
	                String contactNumber, Calendar createTime, Calendar updateTime, DeleteStatus deleteStatus) {
		super(id, province, city, district, detailAddress);
		this.contact = contact;
		this.contactNumber = contactNumber;
		this.location = location;
		this.agency = agency;
		this.deleteStatus = deleteStatus;
		setDescription(description);
		setCreateTime(createTime);
		setUpdateTime(updateTime);
	}

}
