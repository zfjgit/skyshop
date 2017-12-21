/**
 *
 */
package com.sitv.skyshop.massagechair.service.report;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.report.AddrOrderPartitionReportVO;

/**
 * @author zfj20 @ 2018年1月8日
 */
public interface IAddrOrderPartitionReportService {

	PageInfo<AddrOrderPartitionReportVO> findAddrIncomes(SearchParamInfo<OrderIncomePartitionInfo> info);

	PageInfo<OrderInfo> findAddrOrders(SearchParamInfo<OrderIncomePartitionInfo> q);

	PageInfo<OrderIncomePartitionInfo> findAddrIncomeDetails(SearchParamInfo<OrderIncomePartitionInfo> q);
}
