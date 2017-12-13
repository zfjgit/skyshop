/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.service.IDeleteStatusService;

/**
 * @author zfj20 @ 2017年11月17日
 */
public interface IMassageChairService extends IBaseService<MassageChairInfo>, IDeleteStatusService<MassageChairInfo> {

	String NORMAL = ChairStatus.NORMAL.getCode();

	MassageChairInfo getByIMEI(String imei);

	OperateResultInfo checkServiceStatus(Long id);

	void updateStatus(MassageChairInfo chairInfo);

	void asyncCheckServiceStatus(Long id);

}
