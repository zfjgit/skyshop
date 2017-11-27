/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import java.util.List;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.UserRole;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IUserRoleDao extends ICrudDao<UserRole> {

	List<UserRole> findByUser(Long id);

	List<UserRole> findByRole(Long id);

	void deleteByUser(Long id);

	void deleteByRole(Long id);
}
