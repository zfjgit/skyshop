/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.UserRole;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IUserRoleDao extends ICrudDao<UserRole> {

	List<UserRole> findByUser(@Param("id") Long id);

	List<UserRole> findByRole(@Param("id") Long id);

	void deleteByUser(@Param("id") Long id);

	void deleteByRole(@Param("id") Long id);
}
