/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.dto.device.InstallationAddressInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class InstallationAddressService extends CrudService<IInstallationAddressDao, InstallationAddress, InstallationAddressInfo> implements IInstallationAddressService {

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
		InstallationAddress installationAddress = new InstallationAddress(t.getId(), t.getDescription(), t.getFullAddress(), t.getLocation(), t.getContact(), t.getContactNumber());
		update(installationAddress);
	}

	public void createOne(InstallationAddressInfo t) {
		InstallationAddress installationAddress = new InstallationAddress(t.getId(), t.getDescription(), t.getFullAddress(), t.getLocation(), t.getContact(), t.getContactNumber());
		create(installationAddress);
	}

}
