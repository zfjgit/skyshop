/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.device.IPricePackageDao;
import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.device.PricePackage;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Service("massageChairService")
public class MassageChairService extends CrudService<IMassageChairDao, MassageChair, MassageChairInfo> implements IMassageChairService {

	@Autowired
	private IGSMModuleDao gsmModuleDao;

	@Autowired
	private IInstallationAddressDao installationAddressDao;

	@Autowired
	private IPriceDao<Price> priceDao;

	@Autowired
	private IPricePackageDao pricePackageDao;

	@Autowired
	private IAgencyDao agencyDao;

	public MassageChairInfo getOne(Long id) {
		MassageChair massageChair = get(id);
		return MassageChairInfo.create(massageChair);
	}

	public PageInfo<MassageChairInfo> search(SearchParamInfo<MassageChairInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<MassageChair> massageChairs = dao.search(q);

		com.github.pagehelper.PageInfo<MassageChair> pageInfo = new com.github.pagehelper.PageInfo<>(massageChairs, 5);

		List<MassageChairInfo> massageChairInfos = MassageChairInfo.creates(pageInfo.getList());

		return new PageInfo<>(massageChairInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(MassageChairInfo t) {
		GSMModule gsmModule = gsmModuleDao.get(t.getGsmModule().getId());

		InstallationAddress installationAddress = null;
		if (t.getInstallationAddress() != null) {
			installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());
		}
		Agency agency = null;
		if (t.getAgency() != null) {
			agencyDao.get(t.getAgency().getId());
		}

		MassageChair massageChair = get(t.getId());
		massageChair.setAgency(agency);
		massageChair.setBrand(t.getBrand());
		massageChair.setDescription(t.getDescription());
		massageChair.setGsmModule(gsmModule);
		massageChair.setInstallationAddress(installationAddress);
		massageChair.setStatus(BaseEnum.valueOf(ChairStatus.class, t.getStatus().getCode()));
		dao.update(massageChair);

		pricePackageDao.deleteByChair(t.getId());

		StringTokenizer priceIdTokenizer = new StringTokenizer(t.getPriceIds(), ",");
		while (priceIdTokenizer.hasMoreTokens()) {
			String id = priceIdTokenizer.nextToken();
			Price price = priceDao.get(Long.valueOf(id));
			PricePackage pricePackage = new PricePackage(massageChair, price);
			pricePackageDao.insert(pricePackage);
		}
	}

	public void createOne(MassageChairInfo t) {
		GSMModule gsmModule = gsmModuleDao.get(t.getGsmModule().getId());
		InstallationAddress installationAddress = null;
		if (t.getInstallationAddress() != null) {
			installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());
		}
		Agency agency = null;
		if (t.getAgency() != null) {
			agencyDao.get(t.getAgency().getId());
		}

		MassageChair massageChair = new MassageChair(null, t.getName(), t.getDescription(), t.getBrand(), BaseEnum.valueOf(ChairStatus.class, t.getStatus().getCode()), gsmModule,
		                installationAddress, agency, DeleteStatus.NORMAL);

		StringTokenizer priceIdTokenizer = new StringTokenizer(t.getPriceIds(), ",");
		while (priceIdTokenizer.hasMoreTokens()) {
			String id = priceIdTokenizer.nextToken();
			Price price = priceDao.get(Long.valueOf(id));
			PricePackage pricePackage = new PricePackage(massageChair, price);
			pricePackageDao.insert(pricePackage);
		}

		dao.insert(massageChair);
	}

	public MassageChairInfo getByIMEI(String imei) {
		return MassageChairInfo.create(dao.getByIMEI(imei));
	}

	public void updateDeleteStatus(MassageChairInfo t) {
		MassageChair chair = get(t.getId());
		chair.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		dao.updateDeleteStatus(chair);
	}

}
