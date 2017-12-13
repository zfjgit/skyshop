/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;

/**
 * @author zfj20 @ 2017年11月15日
 */
@MyBatisDao
public interface IMassageChairDao extends ICrudDao<MassageChair>, IDeleteStatusDao<MassageChair> {

	MassageChair getByIMEI(@Param("imei") String imei);

	MassageChair getByGSM(@Param("id") Long id);

	MassageChair getBySIM(@Param("sim") String sim);

	List<MassageChair> findByAddr(@Param("id") Long id);

	int getCountByAgency(@Param("id") Long id);

	void updateQRCodeUrl(MassageChair massageChair);

	void updateStatus(MassageChair massageChair);

}
