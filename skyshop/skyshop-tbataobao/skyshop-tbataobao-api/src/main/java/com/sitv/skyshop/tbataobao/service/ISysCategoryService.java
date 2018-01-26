/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;
import java.util.Map;

import com.sitv.skyshop.common.dto.SysCategoryInfo;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 */
public interface ISysCategoryService extends IBaseService<SysCategoryInfo> {

	List<Map<String, Object>> findParents(Long categoryId);

	List<SysCategoryInfo> findChildrens(Long id);
}
