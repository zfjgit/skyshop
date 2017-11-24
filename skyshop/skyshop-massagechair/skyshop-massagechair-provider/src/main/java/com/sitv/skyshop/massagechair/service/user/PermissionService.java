/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.user.IPermissionDao;
import com.sitv.skyshop.massagechair.domain.user.Permission;
import com.sitv.skyshop.massagechair.dto.user.PermissionInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class PermissionService extends CrudService<IPermissionDao, Permission, PermissionInfo> implements IPermissionService {

	public PermissionInfo getOne(Long id) {
		return PermissionInfo.create(get(id));
	}

	public PageInfo<PermissionInfo> search(SearchParamInfo<PermissionInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Permission> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<Permission> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<PermissionInfo> infos = PermissionInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(PermissionInfo t) {
		Permission p = get(t.getId());
		p.setCode(t.getCode());
		p.setName(t.getName());
		p.setUri(t.getUri());
		update(p);
	}

	public void createOne(PermissionInfo t) {
		Permission p = new Permission(t.getCode(), t.getName(), t.getUri());
		create(p);
	}

}
