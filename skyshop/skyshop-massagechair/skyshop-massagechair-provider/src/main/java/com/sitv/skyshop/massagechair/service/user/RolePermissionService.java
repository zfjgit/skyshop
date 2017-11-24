/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.user.IPermissionDao;
import com.sitv.skyshop.massagechair.dao.user.IRoleDao;
import com.sitv.skyshop.massagechair.dao.user.IRolePermissionDao;
import com.sitv.skyshop.massagechair.domain.user.RolePermission;
import com.sitv.skyshop.massagechair.dto.user.RolePermissionInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class RolePermissionService extends CrudService<IRolePermissionDao, RolePermission, RolePermissionInfo> implements IRolePermissionService {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IPermissionDao permissionDao;

	public RolePermissionInfo getOne(Long id) {
		throw new UnsupportedOperationException("错误的查询方式");
	}

	public PageInfo<RolePermissionInfo> search(SearchParamInfo<RolePermissionInfo> q) {
		throw new UnsupportedOperationException("错误的查询方式");
	}

	public void updateOne(RolePermissionInfo t) {
		throw new UnsupportedOperationException("错误的更新方式");
	}

	public void createOne(RolePermissionInfo t) {
		RolePermission r = new RolePermission(roleDao.get(t.getRole().getId()), permissionDao.get(t.getPermission().getId()));
		create(r);
	}

}
