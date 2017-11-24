/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.PricePackage;

/**
 * @author zfj20 @ 2017年11月18日
 */
@MyBatisDao
public interface IPricePackageDao extends ICrudDao<PricePackage> {

	List<PricePackage> findByPrice(Long id);

	List<PricePackage> findByChair(Long id);

	void deleteByPrice(Long id);

	void deleteByChair(Long id);

}
