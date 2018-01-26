/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;
import java.util.Map;

import com.sitv.skyshop.common.dto.ProductCategoryInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 */
public interface IProductCategoryService extends IBaseService<ProductCategoryInfo> {

	List<Map<String, Object>> findParents(Long categoryId);

	List<ProductCategoryInfo> findChildrens(Long id);
}
