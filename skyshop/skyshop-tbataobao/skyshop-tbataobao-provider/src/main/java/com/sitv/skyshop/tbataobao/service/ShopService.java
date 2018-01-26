/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.dao.IShopDao;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark.SubjectType;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;
import com.sitv.skyshop.tbataobao.dto.CheckFailedRemarkInfo;
import com.sitv.skyshop.tbataobao.dto.ShopInfo;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Service
public class ShopService extends BaseUpdateCheckStatusService<ShopInfo, IShopDao> implements IShopService {

	public Map<String, Object> get(Long id) {
		Map<String, Object> shop = dao.get(id);
		if (shop == null) {
			return null;
		}

		if (!CheckStatus.SUCCESSED.getCode().equals(shop.get("ischecked"))) {
			CheckFailedRemark remark = checkFailedRemarkDao.getBy(Long.valueOf(shop.get("id") + ""), SubjectType.SHOP.getCode());
			CheckFailedRemarkInfo remarkInfo = CheckFailedRemarkInfo.create(remark);
			shop.put("checkRemarkInfo", remarkInfo);
		}
		return shop;
	}

	public PageInfo<Map<String, Object>> find(SearchParamInfo<ShopInfo> searchParamInfo) {
		PageHelper.startPage(searchParamInfo.getPage(), searchParamInfo.getPageSize(), true);

		List<Map<String, Object>> shops = dao.find(searchParamInfo);

		com.github.pagehelper.PageInfo<Map<String, Object>> pageInfo = new com.github.pagehelper.PageInfo<>(shops, 5);

		return new PageInfo<>(pageInfo.getList(), searchParamInfo.getPage(), searchParamInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	protected void saveCheckFailRemarkInfo(ShopInfo i) {
		checkFailedRemarkDao.insert(new CheckFailedRemark(i.getId(), SubjectType.SHOP, i.getRemarkInfo().getDescription(), Calendar.getInstance()));
	}

	protected void batchSaveCheckFailRemarkInfo(List<Long> ids, String remark) {
		List<CheckFailedRemark> list = new ArrayList<>();
		for (Long id : ids) {
			list.add(new CheckFailedRemark(id, SubjectType.SHOP, remark, Calendar.getInstance()));
		}
		checkFailedRemarkDao.inserts(list);
	}

	public void update(Map<String, Object> data) {
		dao.update(data);
	}

}
