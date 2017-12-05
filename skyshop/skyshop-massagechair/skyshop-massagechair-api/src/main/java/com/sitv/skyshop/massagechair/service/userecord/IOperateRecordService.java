package com.sitv.skyshop.massagechair.service.userecord;

import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord.OperateType;
import com.sitv.skyshop.massagechair.dto.record.UserOperateRecordInfo;
import com.sitv.skyshop.service.IBaseService;

public interface IOperateRecordService extends IBaseService<UserOperateRecordInfo> {

	String SYSTEM = OperateType.SYSTEM.getCode();
	String AGENCY = OperateType.AGENCY.getCode();
	String ADDRESS = OperateType.ADDRESS.getCode();
	String DEVICE = OperateType.DEVICE.getCode();
	String PRICE = OperateType.PRICE.getCode();
}
