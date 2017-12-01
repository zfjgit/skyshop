/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.massagechair.dao.user.IManagerDao;
import com.sitv.skyshop.massagechair.dao.user.IRoleDao;
import com.sitv.skyshop.massagechair.dao.user.IUserRoleDao;
import com.sitv.skyshop.massagechair.domain.user.Manager;
import com.sitv.skyshop.massagechair.domain.user.Role;
import com.sitv.skyshop.massagechair.domain.user.User.UserStatus;
import com.sitv.skyshop.massagechair.domain.user.User.UserType;
import com.sitv.skyshop.massagechair.domain.user.UserRole;
import com.sitv.skyshop.massagechair.dto.user.ManagerInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class ManagerService extends DefaultUserService<IManagerDao, ManagerInfo, Manager> implements IManagerService {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IUserRoleDao userRoleDao;

	public void createOne(ManagerInfo t) {
		Manager m = new Manager(t.getCode(), t.getName(), t.getDescription(), t.getPassword(), t.getEmail(), t.getMobile(),
		                BaseEnum.valueOf(UserStatus.class, t.getStatus().getCode()), BaseEnum.valueOf(UserType.class, t.getType().getCode()),
		                BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		create(m);
	}

	public void authorize(Long id, List<Long> roles) {
		Manager m = get(id);
		if (roles != null) {
			userRoleDao.deleteByUser(id);

			for (Long rid : roles) {
				Role role = roleDao.get(rid);
				UserRole userRole = new UserRole(m, role);
				userRoleDao.insert(userRole);
			}
		}
	}

}
