/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.device.ISIMCardDao;
import com.sitv.skyshop.massagechair.dao.device.malfunction.ISIMCardMalfunctionDao;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.SIMCardMalfunction;
import com.sitv.skyshop.massagechair.dto.device.malfunction.SIMCardMalfunctionInfo;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class SIMCardMalfunctionService extends DefaultMalfunctionService<ISIMCardMalfunctionDao, SIMCardMalfunction, SIMCardMalfunctionInfo> implements ISIMCardMalfunctionService {

	@Autowired
	private ISIMCardDao deviceDao;

	public void createOne(SIMCardMalfunctionInfo t) {
		SIMCardMalfunction malfunction = new SIMCardMalfunction(t.getId(), t.getName(), t.getDescription());
		malfunction.setStatus(MalfunctionStatus.valueOf(t.getStatus()));
		malfunction.setDevice(deviceDao.get(t.getId()));

		create(malfunction);
	}

}
