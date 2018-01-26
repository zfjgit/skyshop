/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.common.domain.ProductCategory;
import com.sitv.skyshop.common.dto.ProductCategoryInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.tbataobao.dao.IProductCategoryDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@Service
public class ProductCategoryService extends CrudService<IProductCategoryDao, ProductCategory, ProductCategoryInfo> implements IProductCategoryService {

	@Autowired
	private IProductCategoryDao categoryDao;

	public PageInfo<ProductCategoryInfo> search(SearchParamInfo<ProductCategoryInfo> q) {
		return null;
	}

	public void updateOne(ProductCategoryInfo t) {
	}

	public void createOne(ProductCategoryInfo t) {
	}

	public List<Map<String, Object>> findParents(Long categoryId) {
		log.debug("categoryId=" + categoryId);
		List<Map<String, Object>> parentList = new ArrayList<>();
		ProductCategory category = get(categoryId);

		getSiblings(category, parentList);

		for (int i = 0; i < 3; i++) {
			if (parentList.size() > i) {
				Map<String, Object> childrenMap = parentList.get(i);
				childrenMap.put("index", i);
			} else {
				List<ProductCategoryInfo> childrenInfos = new ArrayList<>();
				childrenInfos.add(new ProductCategoryInfo(0l, "请选择", null));
				Map<String, Object> childrenMap = new HashMap<>();
				childrenMap.put("options", childrenInfos);
				childrenMap.put("selectedId", 0);
				childrenMap.put("index", i);
				parentList.add(childrenMap);
			}
		}
		return parentList;
	}

	private void getSiblings(ProductCategory category, List<Map<String, Object>> parentList) {
		if (category == null) {
			return;
		}

		ProductCategory parent = category.getParent();
		Long parentId = 0l;
		if (parent != null) {
			parentId = parent.getId();
		}

		log.debug("parentId=" + parentId);

		List<ProductCategory> children = categoryDao.findChildrens(parentId, category.getShopId());
		List<ProductCategoryInfo> childrenInfos = ProductCategoryInfo.creates(children);
		childrenInfos.add(0, new ProductCategoryInfo(0l, "请选择", null));
		Map<String, Object> childrenMap = new HashMap<>();
		childrenMap.put("options", childrenInfos);
		childrenMap.put("selectedId", category.getId());
		parentList.add(0, childrenMap);

		getSiblings(parent, parentList);
	}

	public ProductCategoryInfo getOne(Long id) {
		return null;
	}

	public List<ProductCategoryInfo> findChildrens(Long id) {
		ProductCategory productCategory = get(id);
		return ProductCategoryInfo.creates(categoryDao.findChildrens(id, productCategory.getShopId()));
	}

}
