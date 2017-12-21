/**
 *
 */
package com.sitv.skyshop.massagechair.service.userecord;

import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IUseRecordService extends IBaseService<UseRecordInfo> {
	void saveOperateResult(String command, String imei, String sim, String code, String orderId, String chairStartTime);

	void createOpenRecord(UseRecordInfo recordInfo);

	void createCheckRecord(UseRecordInfo recordInfo);

	void createAsyncCheckRecord(UseRecordInfo recordInfo);

	void createCloseRecord(UseRecordInfo recordInfo);

	void createResetUrlRecord(UseRecordInfo recordInfo);

	void createRecord(UseRecordInfo recordInfo);

	@Transactional(readOnly = true)
	OperateResultInfo getOperateResult(String key, int retries);

	@Transactional(readOnly = true)
	OperateResultInfo getCheckOperateResult(String sim, int retries);

	@Transactional(readOnly = true)
	OperateResultInfo getOpenOperateResult(String sim, String orderId, int retries);

	@Transactional(readOnly = true)
	OperateResultInfo getCloseOperateResult(String sim, String orderId, int retries);

	@Transactional(readOnly = true)
	OperateResultInfo getResetUrlOperateResult(String sim, int retries);

	@Transactional(readOnly = true)
	UseRecordInfo getByOrder(Long orderId, String type, String resultCode);

}
