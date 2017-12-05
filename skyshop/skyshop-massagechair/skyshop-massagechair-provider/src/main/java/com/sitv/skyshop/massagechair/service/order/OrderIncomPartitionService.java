/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderIncomePartitionDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class OrderIncomPartitionService extends CrudService<IOrderIncomePartitionDao, OrderIncomePartition, OrderIncomePartitionInfo> implements IOrderIncomePartitionService {

	@Autowired
	private IAgencyDao agencyDao;

	@Autowired
	private IOrderDao orderDao;

	public OrderIncomePartitionInfo getOne(Long id) {
		return OrderIncomePartitionInfo.create(get(id));
	}

	public PageInfo<OrderIncomePartitionInfo> search(SearchParamInfo<OrderIncomePartitionInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<OrderIncomePartition> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<OrderIncomePartition> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<OrderIncomePartitionInfo> infos = OrderIncomePartitionInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(OrderIncomePartitionInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());
		Order order = orderDao.get(t.getOrder().getId());

		OrderIncomePartition partition = get(t.getId());
		partition.setAgency(agency);
		partition.setMoney(t.getMoney());
		partition.setTotalMoney(t.getTotalMoney());
		partition.setOrder(order);
		partition.setPercentage(t.getPercentage());
		partition.setUpdateTime(Calendar.getInstance());

		partition.calcCheckCode();

		update(partition);
	}

	public void createOne(OrderIncomePartitionInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());
		Order order = orderDao.get(t.getOrder().getId());
		OrderIncomePartition partition = new OrderIncomePartition(order, agency, t.getPercentage(), t.getMoney(), t.getTotalMoney());
		create(partition);
	}

}
