/**
 *
 */
package com.sitv.skyshop.massagechair.service.price;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.price.IPromotionPriceDao;
import com.sitv.skyshop.massagechair.domain.price.PromotionPrice;
import com.sitv.skyshop.massagechair.dto.price.PromotionPriceInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class PromotionPriceService extends DefaultPriceService<IPromotionPriceDao, PromotionPrice, PromotionPriceInfo> implements IPromotionPriceService {

	public void updateOne(PromotionPriceInfo t) {
		PromotionPrice price = get(t.getId());
		price.setEarlierOn(t.getEarlierOn());
		price.setEarlierOnMoney(t.getEarlierOnMoney());
		price.setPrice(t.getPrice());
		price.setName(t.getName());
		price.setImg(t.getImg());
		update(price);
	}

	public void createOne(PromotionPriceInfo t) {
		PromotionPrice price = new PromotionPrice(null, t.getName(), t.getPrice(), t.getImg());
		price.setEarlierOn(t.getEarlierOn());
		price.setEarlierOnMoney(t.getEarlierOnMoney());
		price.setAgency(agencyDao.get(t.getAgency().getId()));
		create(price);
	}

}
