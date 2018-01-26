/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;

import com.sitv.skyshop.common.domain.SysCategory;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ISysCategoryDao extends ICrudDao<SysCategory> {
	List<SysCategory> findChildrens(Long id);
}
