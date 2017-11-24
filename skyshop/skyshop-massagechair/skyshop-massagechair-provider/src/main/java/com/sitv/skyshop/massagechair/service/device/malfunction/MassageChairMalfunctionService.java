/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.device.malfunction.IMassageChairMalfunctionDao;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.MassageChairMalfunction;
import com.sitv.skyshop.massagechair.dto.device.malfunction.MassageChairMalfunctionInfo;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class MassageChairMalfunctionService extends DefaultMalfunctionService<IMassageChairMalfunctionDao, MassageChairMalfunction, MassageChairMalfunctionInfo>
                implements IMassageChairMalfunctionService {

	@Autowired
	private IMassageChairDao deviceDao;

	public void createOne(MassageChairMalfunctionInfo t) {
		MassageChairMalfunction malfunction = new MassageChairMalfunction(t.getId(), t.getName(), t.getDescription());
		malfunction.setStatus(MalfunctionStatus.valueOf(t.getStatus()));
		malfunction.setDevice(deviceDao.get(t.getId()));

		create(malfunction);
	}
}
