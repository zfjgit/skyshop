/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IGSMModuleDao extends ICrudDao<GSMModule>, IDeleteStatusDao<GSMModule> {

	GSMModule getBySIM(Long id);
}
