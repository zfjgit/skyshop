/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;

/**
 * @author zfj20 @ 2017年11月15日
 */
@MyBatisDao
public interface IMassageChairDao extends ICrudDao<MassageChair>, IDeleteStatusDao<MassageChair> {

	MassageChair getByIMEI(String imei);

	MassageChair getBySIM(String sim);

	List<MassageChair> findByAddr(Long id);

}
