/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.Map;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.dto.ShopInfo;

/**
 * @author zfj20 @ 2018年3月24日
 */
public interface IShopService extends IUpdateCheckStatusService<ShopInfo> {

	Map<String, Object> get(Long id);

	PageInfo<Map<String, Object>> find(SearchParamInfo<ShopInfo> searchParamInfo);

	void update(Map<String, Object> data);
}
