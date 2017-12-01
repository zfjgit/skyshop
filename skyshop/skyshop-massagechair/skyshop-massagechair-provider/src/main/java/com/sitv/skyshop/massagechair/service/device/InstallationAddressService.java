/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.dao.IAddressDao;
import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.dto.device.InstallationAddressInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class InstallationAddressService extends CrudService<IInstallationAddressDao, InstallationAddress, InstallationAddressInfo> implements IInstallationAddressService {

	@Autowired
	private IAgencyDao agencyDao;

	@Autowired
	private IAddressDao addressDao;

	public InstallationAddressInfo getOne(Long id) {
		return InstallationAddressInfo.create(get(id));
	}

	public PageInfo<InstallationAddressInfo> search(SearchParamInfo<InstallationAddressInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<InstallationAddress> installationAddresses = dao.search(q);

		com.github.pagehelper.PageInfo<InstallationAddress> pageInfo = new com.github.pagehelper.PageInfo<>(installationAddresses, 5);

		List<InstallationAddressInfo> installationAddressInfos = InstallationAddressInfo.creates(pageInfo.getList());

		return new PageInfo<>(installationAddressInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(InstallationAddressInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());

		Address province = addressDao.get(t.getProvince().getId());
		Address city = addressDao.get(t.getCity().getId());
		Address district = addressDao.get(t.getDistrict() != null ? t.getDistrict().getId() : null);

		InstallationAddress addr = get(t.getId());
		addr.setAgency(agency);
		addr.setCity(city);
		addr.setContact(t.getContact());
		addr.setContactNumber(t.getContactNumber());
		addr.setDescription(t.getDescription());
		addr.setDetailAddress(t.getDetailAddress());
		addr.setDistrict(district);
		addr.setFullAddress(province.getName() + city.getName() + (district != null ? district.getName() : "") + t.getDetailAddress());
		addr.setLocation(t.getLocation());
		addr.setName(t.getName());
		addr.setProvince(province);
		addr.setUpdateTime(Calendar.getInstance());

		update(addr);
	}

	public void createOne(InstallationAddressInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());

		Address province = addressDao.get(t.getProvince().getId());
		Address city = addressDao.get(t.getCity().getId());
		Address district = addressDao.get(t.getDistrict() != null ? t.getDistrict().getId() : null);

		InstallationAddress installationAddress = new InstallationAddress(null, agency, province, city, district, t.getDetailAddress(), t.getDescription(), t.getLocation(),
		                t.getContact(), t.getContactNumber(), Calendar.getInstance(), null, DeleteStatus.NORMAL);
		create(installationAddress);
	}

	public void updateDeleteStatus(InstallationAddressInfo t) {
		InstallationAddress address = get(t.getId());
		address.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		dao.updateDeleteStatus(address);
	}

}
