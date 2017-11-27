/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import com.sitv.skyshop.massagechair.dto.user.RoleInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IRoleService extends IBaseService<RoleInfo> {

	void authorize(Long id, List<Long> permissions);

}
