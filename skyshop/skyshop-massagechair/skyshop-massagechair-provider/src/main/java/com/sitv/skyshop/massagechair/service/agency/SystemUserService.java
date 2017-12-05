/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.massagechair.dao.user.IUserDao;
import com.sitv.skyshop.massagechair.domain.agency.SystemUser;
import com.sitv.skyshop.massagechair.domain.user.User.UserStatus;
import com.sitv.skyshop.massagechair.domain.user.User.UserType;
import com.sitv.skyshop.massagechair.dto.agency.SystemUserInfo;
import com.sitv.skyshop.massagechair.service.user.DefaultUserService;

/**
 * @author zfj20 @ 2017年12月8日
 */
public class SystemUserService extends DefaultUserService<IUserDao<SystemUser>, SystemUserInfo, SystemUser> implements ISystemUserService {

	public SystemUserInfo getOne(Long id) {
		return SystemUserInfo.create(get(id));
	}

	public void createOne(SystemUserInfo t) {
		SystemUser user = new SystemUser(null, t.getCode(), t.getName(), t.getPassword(), BaseEnum.valueOf(UserStatus.class, t.getStatus().getCode()), UserType.SYSTEM,
		                DeleteStatus.NORMAL);
		create(user);
	}

}
