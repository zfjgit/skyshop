/**
 *
 */
package com.sitv.skyshop.massagechair.dao.price;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.price.Price;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IPriceDao<T extends Price> extends ICrudDao<T> {

	int getCountByAgency(Long id);

}
