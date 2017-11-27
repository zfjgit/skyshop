/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.malfunction.IGSMModuleMalfunctionDao;
import com.sitv.skyshop.massagechair.domain.device.malfunction.GSMModuleMalfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.dto.device.malfunction.GSMModuleMalfunctionInfo;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class GSMModuleMalfunctionService extends DefaultMalfunctionService<IGSMModuleMalfunctionDao, GSMModuleMalfunction, GSMModuleMalfunctionInfo>
                implements IGSMModuleMalfunctionService {

	@Autowired
	private IGSMModuleDao deviceDao;

	public void createOne(GSMModuleMalfunctionInfo t) {
		GSMModuleMalfunction malfunction = new GSMModuleMalfunction(t.getId(), t.getName(), t.getDescription());
		malfunction.setStatus(MalfunctionStatus.valueOf(t.getStatus()));
		malfunction.setDevice(deviceDao.get(t.getId()));

		create(malfunction);
	}

}
