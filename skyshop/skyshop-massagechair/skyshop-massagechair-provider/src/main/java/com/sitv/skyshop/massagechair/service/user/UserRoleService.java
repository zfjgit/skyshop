/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.user.IRoleDao;
import com.sitv.skyshop.massagechair.dao.user.IUserDao;
import com.sitv.skyshop.massagechair.dao.user.IUserRoleDao;
import com.sitv.skyshop.massagechair.domain.user.Manager;
import com.sitv.skyshop.massagechair.domain.user.UserRole;
import com.sitv.skyshop.massagechair.dto.user.UserRoleInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class UserRoleService extends CrudService<IUserRoleDao, UserRole, UserRoleInfo> implements IUserRoleService {

	@Autowired
	private IUserDao<Manager> managerDao;

	@Autowired
	private IRoleDao roleDao;

	public UserRoleInfo getOne(Long id) {
		throw new UnsupportedOperationException("错误的查询方式");
	}

	public PageInfo<UserRoleInfo> search(SearchParamInfo<UserRoleInfo> q) {
		throw new UnsupportedOperationException("错误的查询方式");
	}

	public void updateOne(UserRoleInfo t) {
		throw new UnsupportedOperationException("错误的更新方式");
	}

	public void createOne(UserRoleInfo t) {
		UserRole ur = new UserRole(managerDao.get(t.getId()), roleDao.get(t.getRole().getId()));
		create(ur);
	}

}
