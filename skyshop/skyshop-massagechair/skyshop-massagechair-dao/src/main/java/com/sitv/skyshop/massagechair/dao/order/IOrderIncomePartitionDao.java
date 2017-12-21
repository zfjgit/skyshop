/**
 *
 */
package com.sitv.skyshop.massagechair.dao.order;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@MyBatisDao
public interface IOrderIncomePartitionDao extends ICrudDao<OrderIncomePartition> {
	List<OrderIncomePartition> findByOrder(@Param("id") Long id);

	List<OrderIncomePartition> findByAgency(@Param("id") Long id);

	int getCountByAgency(@Param("id") Long id);

	BigDecimal getTotalMoney(SearchParamInfo<OrderIncomePartitionInfo> info);

	List<OrderIncomePartition> findChairIncomes(SearchParamInfo<OrderIncomePartitionInfo> q);

	List<OrderIncomePartition> findChairIncomeDetail(SearchParamInfo<OrderIncomePartitionInfo> q);

	BigDecimal getChairTotalMoney(SearchParamInfo<OrderIncomePartitionInfo> q);

	BigDecimal getTodayTotalMoney(@Param("id") Long id, @Param("startTime") Calendar startTime, @Param("endTime") Calendar endTime);

	List<OrderIncomePartition> findOrderPartitions(SearchParamInfo<OrderIncomePartitionInfo> q);

	void deleteByOrder(@Param("orderId") Long orderId);
}
