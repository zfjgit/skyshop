/**
 *
 */
package com.sitv.skyshop.massagechair.dao.report;

import java.math.BigDecimal;
import java.util.List;

import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.massagechair.report.AddrOrderPartitionReportVO;

/**
 * @author zfj20 @ 2017年11月20日
 */
@MyBatisDao
public interface IOrderIncomePartitionReportDao {

	List<AddrOrderPartitionReportVO> findAddrIncomes(SearchParamInfo<OrderIncomePartitionInfo> q);

	BigDecimal getAddrIncomeTotalMoney(SearchParamInfo<OrderIncomePartitionInfo> q);

	List<Order> findAddrOrders(SearchParamInfo<OrderIncomePartitionInfo> q);

	BigDecimal getAddrOrderTotalMoney(SearchParamInfo<OrderIncomePartitionInfo> q);

	List<OrderIncomePartition> findAddrIncomeDetail(SearchParamInfo<OrderIncomePartitionInfo> q);
}
