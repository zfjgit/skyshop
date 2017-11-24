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
		FixedPrice fixedPrice = new FixedPrice();
		fixedPrice.setPrice(t.getPrice());
		fixedPrice.setName(t.getName());
		create(fixedPrice);
	}

}
