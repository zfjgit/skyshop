/**
 *
 */
package com.sitv.skyshop.massagechair.dao.order;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@MyBatisDao
public interface IOrderDao extends ICrudDao<Order>, IDeleteStatusDao<Order> {
	List<Order> findByChair(@Param("id") Long id);

	List<Order> findByAgency(@Param("id") Long id);

	int getCountByAgency(@Param("id") Long id);

	void updatePayStatus(Order order);

	void updateChairStartTime(@Param("chairStartTime") Calendar chairStartTime, @Param("id") Long id);

	Order getByPayStatus(@Param("id") Long id, @Param("payStatus") PayStatus payStatus);

	Order getByCode(@Param("orderCode") String orderCode);

	BigDecimal getAgencyOrderTotalMoney(SearchParamInfo<OrderInfo> info);
}
