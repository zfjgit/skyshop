/**
 *
 */
package com.sitv.skyshop.massagechair.dao.order;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;

/**
 * @author zfj20 @ 2017年11月20日
 */
@MyBatisDao
public interface IOrderIncomePartitionDao extends ICrudDao<OrderIncomePartition> {
	List<OrderIncomePartition> findByOrder(Long id);

	List<OrderIncomePartition> findByAgency(Long id);

	int getCountByAgency(Long id);
}
