/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.Role;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IRoleDao extends ICrudDao<Role> {
	Role get(@Param("code") String code);
}
