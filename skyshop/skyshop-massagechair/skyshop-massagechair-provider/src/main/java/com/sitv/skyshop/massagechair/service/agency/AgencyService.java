/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderIncomePartitionDao;
import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.Agency.AgencyLevel;
import com.sitv.skyshop.massagechair.domain.price.FixedPrice;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.agency.AgencyOverviewInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class AgencyService extends CrudService<IAgencyDao, Agency, AgencyInfo> implements IAgencyService {

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IMassageChairDao chairDao;

	@Autowired
	private IPriceDao<FixedPrice> priceDao;

	@Autowired
	private IInstallationAddressDao addressDao;

	@Autowired
	private IOrderIncomePartitionDao orderIncomePartitionDao;

	public AgencyInfo getOne(Long id) {
		return AgencyInfo.create(get(id));
	}

	public PageInfo<AgencyInfo> search(SearchParamInfo<AgencyInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Agency> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<Agency> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyInfo> infos = AgencyInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(AgencyInfo t) {
		Agency agency = get(t.getId());
		agency.setBalance(t.getBalance());
		agency.setName(t.getName());
		agency.setLevel(BaseEnum.valueOf(AgencyLevel.class, t.getLevel().getCode()));
		agency.setOrderIncomePercentage(t.getOrderIncomePercentage());
		agency.setParent(get(t.getParent().getId()));
		agency.setUpdateTime(Calendar.getInstance());
		agency.calcCheckCode();
		update(agency);
	}

	public void createOne(AgencyInfo t) {
		Agency parent = get(t.getParent().getId());
		Agency agency = new Agency(null, t.getName(), parent, BaseEnum.valueOf(AgencyLevel.class, t.getLevel().getCode()), t.getOrderIncomePercentage(), t.getBalance(),
		                DeleteStatus.NORMAL);
		agency.calcCheckCode();
		create(agency);

		agency.calcCheckCode();
		update(agency);
	}

	public void updateDeleteStatus(AgencyInfo t) {
		Agency agency = get(t.getId());
		agency.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		agency.calcCheckCode();

		dao.updateDeleteStatus(agency);
	}

	public AgencyOverviewInfo getOverview(Long id) {
		// 今日收益
		BigDecimal total = orderDao.getTotalMoney(id, Utils.getTodayStartTime(), Utils.getTodayEndTime());

		// 收益分成
		int orderPartitionCount = orderIncomePartitionDao.getCountByAgency(id);

		// 订单
		int orderCount = orderDao.getCountByAgency(id);

		// 设备
		int chairCount = chairDao.getCountByAgency(id);

		// 场所
		int addrCount = addressDao.getCountByAgency(id);

		// 下级代理商
		int agencyCount = dao.getCountByAgency(id);

		// 价格
		int priceCount = priceDao.getCountByAgency(id);

		AgencyOverviewInfo overviewInfo = new AgencyOverviewInfo(chairCount, addrCount, agencyCount, priceCount, orderCount, orderPartitionCount, total);

		return overviewInfo;
	}
}
