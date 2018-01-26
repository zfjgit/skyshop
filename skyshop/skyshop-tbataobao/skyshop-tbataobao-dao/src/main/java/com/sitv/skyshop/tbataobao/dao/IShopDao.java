/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.dto.ShopInfo;

/**
 * @author zfj20 @ 2018年3月24日
 */
@MyBatisDao
public interface IShopDao extends IUpdateCheckStatusDao {
	Map<String, Object> get(@Param("id") Long id);

	List<Map<String, Object>> find(SearchParamInfo<ShopInfo> params);

	void update(Map<String, Object> data);
}
