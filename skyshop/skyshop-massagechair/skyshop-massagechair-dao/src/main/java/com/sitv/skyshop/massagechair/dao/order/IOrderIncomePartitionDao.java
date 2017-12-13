/**
 *
 */
package com.sitv.skyshop.massagechair.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;

/**
 * @author zfj20 @ 2017年11月20日
 */
@MyBatisDao
public interface IOrderIncomePartitionDao extends ICrudDao<OrderIncomePartition> {
	List<OrderIncomePartition> findByOrder(@Param("id") Long id);

	List<OrderIncomePartition> findByAgency(@Param("id") Long id);

	int getCountByAgency(@Param("id") Long id);
}
