/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IOrderIncomePartitionService extends IBaseService<OrderIncomePartitionInfo> {

	PageInfo<OrderIncomePartitionInfo> findChairIncome(SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo);

	PageInfo<OrderIncomePartitionInfo> findChairIncomeDetail(SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo);

	PageInfo<OrderIncomePartitionInfo> findOrderPartitions(SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo);

}
