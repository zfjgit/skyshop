/**
 *
 */
package com.sitv.skyshop.massagechair.service.price;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.price.MinutePrice;
import com.sitv.skyshop.massagechair.dto.price.MinutePriceInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class MinutePriceService extends DefaultPriceService<IPriceDao<MinutePrice>, MinutePrice, MinutePriceInfo> implements IMinutePriceService {

	public void createOne(MinutePriceInfo t) {
		MinutePrice minutePrice = new MinutePrice();
		minutePrice.setPrice(t.getPrice());
		minutePrice.setName(t.getName());
		create(minutePrice);
	}

}
