/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.user.IPermissionDao;
import com.sitv.skyshop.massagechair.dao.user.IRoleDao;
import com.sitv.skyshop.massagechair.dao.user.IRolePermissionDao;
import com.sitv.skyshop.massagechair.domain.user.Permission;
import com.sitv.skyshop.massagechair.domain.user.Role;
import com.sitv.skyshop.massagechair.domain.user.RolePermission;
import com.sitv.skyshop.massagechair.dto.user.RoleInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class RoleService extends CrudService<IRoleDao, Role, RoleInfo> implements IRoleService {

	@Autowired
	private IRolePermissionDao rolePermissionDao;

	@Autowired
	private IPermissionDao permissionDao;

	public RoleInfo getOne(Long id) {
		return RoleInfo.create(get(id));
	}

	public PageInfo<RoleInfo> search(SearchParamInfo<RoleInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Role> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<Role> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<RoleInfo> infos = RoleInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(RoleInfo t) {
		Role r = get(t.getId());
		r.setCode(t.getCode());
		r.setName(t.getName());
		r.setSuper(t.isSuper());
		update(r);
	}

	public void createOne(RoleInfo t) {
		Role r = new Role(t.getCode(), t.getName(), t.isSuper());
		create(r);
	}

	public void authorize(Long id, List<Long> permissions) {
		Role role = get(id);
		if (permissions != null) {
			rolePermissionDao.deleteByRole(id);

			for (Long pid : permissions) {
				Permission permission = permissionDao.get(pid);
				RolePermission rp = new RolePermission(role, permission);
				rolePermissionDao.insert(rp);
			}
		}
	}

}
