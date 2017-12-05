/**
 *
 */
package com.sitv.skyshop.massagechair.dao.device;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IInstallationAddressDao extends ICrudDao<InstallationAddress>, IDeleteStatusDao<InstallationAddress> {

	List<InstallationAddress> findByAgency(Long agencyId);

	int getCountByAgency(Long id);
}
