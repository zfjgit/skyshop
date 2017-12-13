/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.PricePackage;

/**
 * @author zfj20 @ 2017年11月18日
 */
@MyBatisDao
public interface IPricePackageDao extends ICrudDao<PricePackage> {

	List<PricePackage> findByPrice(@Param("id") Long id);

	List<PricePackage> findByChair(@Param("id") Long id);

	void deleteByPrice(@Param("id") Long id);

	void deleteByChair(@Param("id") Long id);

}
