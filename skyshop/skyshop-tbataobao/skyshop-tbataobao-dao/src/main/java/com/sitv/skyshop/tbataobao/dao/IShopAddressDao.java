/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.tbataobao.domain.ShopAddress;

/**
 * @author zfj20
 */
@MyBatisDao
public interface IShopAddressDao extends ICrudDao<ShopAddress> {

	List<ShopAddress> findChildrens(@Param("parentId") Long parentId);
}
