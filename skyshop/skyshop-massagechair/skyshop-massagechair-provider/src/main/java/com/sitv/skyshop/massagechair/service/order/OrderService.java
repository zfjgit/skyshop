/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderDao;
import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.Order.OrderStatus;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class OrderService extends CrudService<IOrderDao, Order, OrderInfo> implements IOrderService {

	private IMassageChairDao massageChairDao;
	private IPriceDao<?> priceDao;

	public OrderInfo getOne(Long id) {
		return OrderInfo.create(get(id));
	}

	public PageInfo<OrderInfo> search(SearchParamInfo<OrderInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Order> orders = dao.search(q);

		com.github.pagehelper.PageInfo<Order> pageInfo = new com.github.pagehelper.PageInfo<>(orders, 5);

		List<OrderInfo> orderInfos = OrderInfo.creates(pageInfo.getList());

		return new PageInfo<>(orderInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(OrderInfo t) {
		MassageChair chair = massageChairDao.get(t.getChair().getId());
		Price price = priceDao.get(t.getId());

		Order order = get(t.getId());
		order.setChair(chair);
		order.setCompleteTime(t.getCompleteTime());
		order.setCreateTime(t.getCreateTime());
		order.setDescription(t.getDescription());
		order.setMinutes(t.getMinutes());
		order.setMoney(t.getMoney());
		order.setOrderStatus(OrderStatus.valueOf(t.getOrderStatus()));
		order.setPayStatus(PayStatus.valueOf(t.getPayStatus()));
		order.setPrice(price);
		order.setRealMins(t.getRealMins());

		update(order);
	}

	public void createOne(OrderInfo t) {
		MassageChair chair = massageChairDao.get(t.getChair().getId());
		Price price = priceDao.get(t.getId());

		Calendar c = Calendar.getInstance();
		String code = Utils.time2String(c, "yyyyMMddHHmmss") + c.get(Calendar.MILLISECOND);
		Order order = new Order(code, t.getDescription(), t.getMinutes(), t.getMoney(), t.getRealMins(), t.getCompleteTime(), t.getOrderStatus(), t.getPayStatus(), price, chair);
		create(order);
	}

}
