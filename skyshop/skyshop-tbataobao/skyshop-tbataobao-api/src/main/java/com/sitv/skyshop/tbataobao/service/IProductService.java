/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.Map;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.dto.ProductInfo;

/**
 * @author zfj20 @ 2018年3月23日
 */
public interface IProductService extends IUpdateCheckStatusService<ProductInfo> {

	Map<String, Object> get(Long id);

	PageInfo<Map<String, Object>> find(SearchParamInfo<ProductInfo> searchParamInfo);

	void update(Map<String, Object> data);
}
