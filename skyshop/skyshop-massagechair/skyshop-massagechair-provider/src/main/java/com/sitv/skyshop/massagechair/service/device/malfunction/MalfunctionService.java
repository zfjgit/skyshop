/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.device.malfunction.IMalfunctionDao;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.dto.device.malfunction.MalfunctionInfo;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Service
public class MalfunctionService extends DefaultMalfunctionService<IMalfunctionDao<Malfunction>, Malfunction, MalfunctionInfo> implements IMalfunctionService<MalfunctionInfo> {

}
