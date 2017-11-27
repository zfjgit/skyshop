/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.RolePermission;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IRolePermissionDao extends ICrudDao<RolePermission> {

	List<RolePermission> findByPermission(Long id);

	List<RolePermission> findByRole(Long id);

	void deleteByRole(Long id);

	void deleteByPermission(Long id);
}
