/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.RolePermission;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IRolePermissionDao extends ICrudDao<RolePermission> {

	List<RolePermission> findByPermission(@Param("id") Long id);

	List<RolePermission> findByRole(@Param("id") Long id);

	void deleteByRole(@Param("id") Long id);

	void deleteByPermission(@Param("id") Long id);
}
