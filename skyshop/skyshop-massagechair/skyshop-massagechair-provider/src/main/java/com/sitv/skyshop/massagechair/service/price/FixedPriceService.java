/**
 *
 */
package com.sitv.skyshop.massagechair.service.price;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.price.FixedPrice;
import com.sitv.skyshop.massagechair.dto.price.FixedPriceInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class FixedPriceService extends DefaultPriceService<IPriceDao<FixedPrice>, FixedPrice, FixedPriceInfo> implements IFixedPriceService {

	public void createOne(FixedPriceInfo t) {
		FixedPrice fixedPrice = new FixedPrice(null, t.getName(), t.getPrice(), t.getImg(), t.getMinutes());
		create(fixedPrice);
	}

	public void updateOne(FixedPriceInfo t) {
		FixedPrice price = get(t.getId());
		price.setPrice(t.getPrice());
		price.setName(t.getName());
		price.setImg(t.getImg());
		price.setMinutes(t.getMinutes());
		update(price);
	}

}
