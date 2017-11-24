/**
 *
 */
package com.sitv.skyshop.massagechair.service.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Service
public class PriceService extends DefaultPriceService<IPriceDao<Price>, Price, PriceInfo> implements IPriceService<PriceInfo> {

	private static final Logger log = LoggerFactory.getLogger(PriceService.class);

	public PriceInfo getOne(Long id) {
		log.info("getOne:id=" + id);
		return super.getOne(id);
	}

	public void updateOne(PriceInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}
}
