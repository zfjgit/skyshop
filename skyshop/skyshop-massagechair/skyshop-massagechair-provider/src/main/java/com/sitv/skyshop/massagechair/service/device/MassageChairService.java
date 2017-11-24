/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.device.IPricePackageDao;
import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.PricePackage;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;
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
		InstallationAddress installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());

		MassageChair massageChair = new MassageChair(t.getId(), t.getName(), t.getDescription(), t.getBrand(), t.isPromotionPrice(), t.getStatus(), gsmModule, installationAddress);

		pricePackageDao.deleteByChair(t.getId());

		StringTokenizer priceIdTokenizer = new StringTokenizer(t.getPriceIds(), ",");
		while (priceIdTokenizer.hasMoreTokens()) {
			String id = priceIdTokenizer.nextToken();
			Price price = priceDao.get(Long.valueOf(id));
			PricePackage pricePackage = new PricePackage(massageChair, price);
			pricePackageDao.insert(pricePackage);
		}

		dao.update(massageChair);
	}

	public void createOne(MassageChairInfo t) {
		GSMModule gsmModule = gsmModuleDao.get(t.getGsmModule().getId());
		InstallationAddress installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());

		MassageChair massageChair = new MassageChair(t.getId(), t.getName(), t.getDescription(), t.getBrand(), t.isPromotionPrice(), t.getStatus(), gsmModule, installationAddress);

		StringTokenizer priceIdTokenizer = new StringTokenizer(t.getPriceIds(), ",");
		while (priceIdTokenizer.hasMoreTokens()) {
			String id = priceIdTokenizer.nextToken();
			Price price = priceDao.get(Long.valueOf(id));
			PricePackage pricePackage = new PricePackage(massageChair, price);
			pricePackageDao.insert(pricePackage);
		}

		dao.insert(massageChair);
	}

}
