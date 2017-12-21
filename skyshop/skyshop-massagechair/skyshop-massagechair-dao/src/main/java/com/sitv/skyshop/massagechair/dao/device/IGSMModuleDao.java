/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IGSMModuleDao extends ICrudDao<GSMModule>, IDeleteStatusDao<GSMModule> {

	GSMModule getBySIM(@Param("id") Long id);

	void updateStatus(GSMModule gsmModule);

	GSMModule getByIMEI(@Param("imei") String imei);
}
