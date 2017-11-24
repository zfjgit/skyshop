/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import com.sitv.skyshop.massagechair.dto.user.ManagerInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IManagerService extends IUserService<ManagerInfo> {
	void authorize(Long id, List<Long> roles);
}
