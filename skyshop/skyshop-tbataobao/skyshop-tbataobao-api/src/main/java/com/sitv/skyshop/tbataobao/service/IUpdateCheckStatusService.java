/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import com.sitv.skyshop.tbataobao.dto.BaseCheckStatusInfo;

/**
 * @author zfj20 @ 2018年3月24日
 */
public interface IUpdateCheckStatusService<T extends BaseCheckStatusInfo> {

	void updateCheckStatus(T checkStatusInfo);

	void batchCheck(String code, List<Long> ids, String remark);
}
