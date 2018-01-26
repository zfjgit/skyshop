/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.dao.ICheckFailedRemarkDao;
import com.sitv.skyshop.tbataobao.dao.IProductDao;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark.SubjectType;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;
import com.sitv.skyshop.tbataobao.dto.CheckFailedRemarkInfo;
import com.sitv.skyshop.tbataobao.dto.ProductInfo;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Service("productService")
public class ProductService extends BaseUpdateCheckStatusService<ProductInfo, IProductDao> implements IProductService {

	@Autowired
	private ICheckFailedRemarkDao checkFailedRemarkDao;

	public Map<String, Object> get(Long id) {
		Map<String, Object> product = dao.get(id);
		if (product == null) {
			return null;
		}

		if (!CheckStatus.SUCCESSED.getCode().equals(product.get("ischecked"))) {
			CheckFailedRemark remark = checkFailedRemarkDao.getBy(Long.valueOf(product.get("productIdx") + ""), SubjectType.PRODUCT.getCode());
			CheckFailedRemarkInfo remarkInfo = CheckFailedRemarkInfo.create(remark);
			product.put("checkRemarkInfo", remarkInfo);
		}

		return product;
	}

	public PageInfo<Map<String, Object>> find(SearchParamInfo<ProductInfo> searchParamInfo) {
		PageHelper.startPage(searchParamInfo.getPage(), searchParamInfo.getPageSize(), true);

		List<Map<String, Object>> products = dao.find(searchParamInfo);

		com.github.pagehelper.PageInfo<Map<String, Object>> pageInfo = new com.github.pagehelper.PageInfo<>(products, 5);

		return new PageInfo<>(pageInfo.getList(), searchParamInfo.getPage(), searchParamInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	protected void saveCheckFailRemarkInfo(ProductInfo i) {
		checkFailedRemarkDao.insert(new CheckFailedRemark(i.getId(), SubjectType.PRODUCT, i.getRemarkInfo().getDescription(), Calendar.getInstance()));
	}

	protected void batchSaveCheckFailRemarkInfo(List<Long> ids, String remark) {
		List<CheckFailedRemark> list = new ArrayList<>();
		for (Long id : ids) {
			list.add(new CheckFailedRemark(id, SubjectType.PRODUCT, remark, Calendar.getInstance()));
		}
		checkFailedRemarkDao.inserts(list);
	}

	public void update(Map<String, Object> data) {
		data.put("checkStatusCode", CheckStatus.UNCHECKED.getCode());

		dao.update(data);
	}
}
