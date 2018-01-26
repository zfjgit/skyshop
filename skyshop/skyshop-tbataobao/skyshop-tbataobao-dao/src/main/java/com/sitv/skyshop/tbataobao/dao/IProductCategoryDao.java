/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.common.domain.ProductCategory;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;

/**
 * @author zfj20
 */
@MyBatisDao
public interface IProductCategoryDao extends ICrudDao<ProductCategory> {
	List<ProductCategory> findChildrens(@Param("id") Long id, @Param("shopId") Long shopId);
}
