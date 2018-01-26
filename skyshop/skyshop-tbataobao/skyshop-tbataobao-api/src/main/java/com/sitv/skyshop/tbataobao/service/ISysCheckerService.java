/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.tbataobao.dto.SysCheckerInfo;

/**
 * @author zfj20 @ 2018年3月24日
 */
public interface ISysCheckerService extends IBaseService<SysCheckerInfo> {

	SysCheckerInfo login(SysCheckerInfo i);

	void logout(String account);
}
