/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.tbataobao.dao.ICheckFailedRemarkDao;
import com.sitv.skyshop.tbataobao.dao.IUpdateCheckStatusDao;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;
import com.sitv.skyshop.tbataobao.dto.BaseCheckStatusInfo;
import com.sitv.skyshop.tbataobao.service.IUpdateCheckStatusService;

/**
 * @author zfj20 @ 2018年3月24日
 */
public abstract class BaseUpdateCheckStatusService<I extends BaseCheckStatusInfo, D extends IUpdateCheckStatusDao> implements IUpdateCheckStatusService<I> {
	protected D dao;

	@Autowired
	protected ICheckFailedRemarkDao checkFailedRemarkDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCheckStatus(I i) {
		dao.updateCheckStatus(i.getId(), i.getCheckStatus().getCode());

		if (i.getCheckStatus().getCode().equals(CheckStatus.FAILED.getCode())) {
			saveCheckFailRemarkInfo(i);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void batchCheck(String code, List<Long> ids, String remark) {
		dao.batchUpdateCheckStatus(code, ids);

		if (code.equals(CheckStatus.FAILED.getCode())) {
			batchSaveCheckFailRemarkInfo(ids, remark);
		}
	}

	protected abstract void batchSaveCheckFailRemarkInfo(List<Long> ids, String remark);

	protected abstract void saveCheckFailRemarkInfo(I i);

	@Autowired
	public void setDao(D dao) {
		this.dao = dao;
	}
}
