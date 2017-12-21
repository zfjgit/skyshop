/**
 *
 */
package com.sitv.skyshop.massagechair.service.report;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.report.IOrderIncomePartitionReportDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.report.AddrOrderPartitionReportVO;

/**
 * @author zfj20 @ 2018年1月8日
 */
@Service
public class AddrOrderPartitionReportService implements IAddrOrderPartitionReportService {

	@Autowired
	private IOrderIncomePartitionReportDao dao;

	@Autowired
	private IMassageChairDao chairDao;

	public PageInfo<AddrOrderPartitionReportVO> findAddrIncomes(SearchParamInfo<OrderIncomePartitionInfo> info) {
		PageHelper.startPage(info.getPage(), info.getPageSize(), true);

		List<AddrOrderPartitionReportVO> list = dao.findAddrIncomes(info);

		BigDecimal totalMoney = dao.getAddrIncomeTotalMoney(info);

		if (list != null) {
			AgencyInfo agency = info.getParam().getAgency();
			for (AddrOrderPartitionReportVO vo : list) {
				if (agency == null) {
					vo.setAgencyId(0l);
					vo.setAgencyName("平台");
				} else {
					vo.setAgencyId(agency.getId());
					vo.setAgencyName(agency.getName());
				}
				List<MassageChair> chairs = chairDao.findByAddr(vo.getAddrId());
				if (chairs == null) {
					vo.setChairCount(0);
				} else {
					vo.setChairCount(chairs.size());
				}
				if (vo.getChairCount() > 0) {
					vo.setChairAvgMoney(vo.getMoney().divide(new BigDecimal(vo.getChairCount())));
				} else {
					vo.setChairAvgMoney(vo.getMoney());
				}
			}
		}

		com.github.pagehelper.PageInfo<AddrOrderPartitionReportVO> pageInfo = new com.github.pagehelper.PageInfo<>(list, 5);

		List<AddrOrderPartitionReportVO> infos = pageInfo.getList();

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), totalMoney);
	}

	public PageInfo<OrderInfo> findAddrOrders(SearchParamInfo<OrderIncomePartitionInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Order> entitys = dao.findAddrOrders(q);

		BigDecimal totalMoney = dao.getAddrOrderTotalMoney(q);

		com.github.pagehelper.PageInfo<Order> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<OrderInfo> infos = OrderInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), totalMoney);
	}

	public PageInfo<OrderIncomePartitionInfo> findAddrIncomeDetails(SearchParamInfo<OrderIncomePartitionInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<OrderIncomePartition> entitys = dao.findAddrIncomeDetail(q);

		BigDecimal totalMoney = dao.getAddrIncomeTotalMoney(q);

		com.github.pagehelper.PageInfo<OrderIncomePartition> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<OrderIncomePartitionInfo> infos = OrderIncomePartitionInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), totalMoney);
	}
}
