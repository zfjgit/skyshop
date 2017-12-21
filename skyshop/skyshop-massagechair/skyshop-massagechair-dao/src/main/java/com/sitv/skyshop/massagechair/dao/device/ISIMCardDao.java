/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.SIMCard;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface ISIMCardDao extends ICrudDao<SIMCard>, IDeleteStatusDao<SIMCard> {
	void updateStatus(SIMCard simCard);

	SIMCard getBySIM(@Param("sim") String sim);
}
