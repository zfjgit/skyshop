/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device.malfunction;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;

/**
 * @author zfj20 @ 2017年11月23日
 */
@MyBatisDao
public interface IMalfunctionDao<T extends Malfunction> extends ICrudDao<T> {

	List<T> findByChair(@Param("id") Long id);

	List<T> findByAgency(@Param("id") Long id);

	void updateStatus(Malfunction malfunction);
}
