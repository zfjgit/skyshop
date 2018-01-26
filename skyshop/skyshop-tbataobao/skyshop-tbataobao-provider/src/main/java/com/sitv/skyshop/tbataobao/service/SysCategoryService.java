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

import com.sitv.skyshop.common.domain.SysCategory;
import com.sitv.skyshop.common.dto.SysCategoryInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.tbataobao.dao.ISysCategoryDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@Service
public class SysCategoryService extends CrudService<ISysCategoryDao, SysCategory, SysCategoryInfo> implements ISysCategoryService {

	@Autowired
	private ISysCategoryDao sysCategoryDao;

	public PageInfo<SysCategoryInfo> search(SearchParamInfo<SysCategoryInfo> q) {
		return null;
	}

	public void updateOne(SysCategoryInfo t) {
	}

	public void createOne(SysCategoryInfo t) {
	}

	public List<Map<String, Object>> findParents(Long categoryId) {
		log.debug("categoryId=" + categoryId);
		List<Map<String, Object>> parentList = new ArrayList<>();
		SysCategory sysCategory = get(categoryId);

		getSiblings(sysCategory, parentList);

		for (int i = 0; i < 3; i++) {
			if (parentList.size() > i) {
				Map<String, Object> childrenMap = parentList.get(i);
				childrenMap.put("index", i);
			} else {
				List<SysCategoryInfo> childrenInfos = new ArrayList<>();
				childrenInfos.add(new SysCategoryInfo(0l, "请选择", null));
				Map<String, Object> childrenMap = new HashMap<>();
				childrenMap.put("options", childrenInfos);
				childrenMap.put("selectedId", 0);
				childrenMap.put("index", i);
				parentList.add(childrenMap);
			}
		}

		return parentList;
	}

	private void getSiblings(SysCategory sysCategory, List<Map<String, Object>> parentList) {
		if (sysCategory == null) {
			return;
		}
		log.debug("sysCategory.id" + sysCategory.getId());

		SysCategory parent = sysCategory.getParent();

		Long parentId = 0l;
		if (parent != null) {
			parentId = parent.getId();
		}

		List<SysCategory> children = sysCategoryDao.findChildrens(parentId);
		List<SysCategoryInfo> childrenInfos = SysCategoryInfo.creates(children);
		childrenInfos.add(0, new SysCategoryInfo(0l, "请选择", null));
		Map<String, Object> childrenMap = new HashMap<>();
		childrenMap.put("options", childrenInfos);
		childrenMap.put("selectedId", sysCategory.getId());
		parentList.add(0, childrenMap);

		getSiblings(parent, parentList);
	}

	public SysCategoryInfo getOne(Long id) {
		return null;
	}

	public List<SysCategoryInfo> findChildrens(Long id) {
		return SysCategoryInfo.creates(sysCategoryDao.findChildrens(id));
	}

}
