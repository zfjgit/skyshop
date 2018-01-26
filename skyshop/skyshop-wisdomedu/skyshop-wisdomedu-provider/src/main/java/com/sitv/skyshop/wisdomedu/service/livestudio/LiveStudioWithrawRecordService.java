/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.common.exception.InsufficientBalanceException;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioBalanceDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioWithrawRecordDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioBalance;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord.WithrawStatus;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioWithrawRecordInfo;

/**
 * @author zfj20
 */
@Service
public class LiveStudioWithrawRecordService extends CrudService<ILiveStudioWithrawRecordDao, LiveStudioWithrawRecord, LiveStudioWithrawRecordInfo>
                implements ILiveStudioWithrawRecordService {

	@Autowired
	private ILiveStudioBalanceDao balanceDao;

	@Autowired
	private ILiveStudioDao liveStudioDao;

	public LiveStudioWithrawRecordInfo getOne(Long id) {
		return null;
	}

	public PageInfo<LiveStudioWithrawRecordInfo> search(SearchParamInfo<LiveStudioWithrawRecordInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<LiveStudioWithrawRecordInfo> infos = LiveStudioWithrawRecordInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<LiveStudioWithrawRecordInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(LiveStudioWithrawRecordInfo t) {
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createOne(LiveStudioWithrawRecordInfo t) {
		LiveStudioBalance balance = balanceDao.getBy(t.getLiveStudio().getId());

		BigDecimal afterAmount = balance.getAvailableAmount().subtract(t.getAmount());
		if (afterAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("提现金额必须小于可提现余额：amount=" + t.getAmount() + "，balance=" + balance.getAvailableAmount());
		}

		LiveStudioWithrawRecord record = new LiveStudioWithrawRecord();
		record.setAfterAmount(afterAmount);
		record.setAmount(t.getAmount());
		record.setBankName(t.getBankName());
		record.setBankNum(t.getBankNum());
		record.setBankUser(t.getBankUser());
		record.setBeforeAmount(balance.getAvailableAmount());
		record.setCreateTime(Calendar.getInstance());
		record.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		record.setDescription(t.getDescription());
		record.setStatus(WithrawStatus.NEW);

		create(record);

		t.setId(record.getId());
		t.setStatus(EnumInfo.valueOf(WithrawStatus.class, WithrawStatus.PROCESSING.getCode()));
		updateStatus(t);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStatus(LiveStudioWithrawRecordInfo info) {
		LiveStudioWithrawRecord record = get(info.getId());
		if (record == null) {
			throw new EntityNotFoundException("提现记录不存在：id=" + info.getId());
		}
		if (info.getStatus().getCode().equals(WithrawStatus.PROCESSING.getCode())) {
			LiveStudioBalance balance = balanceDao.getBy(info.getLiveStudio().getId());
			BigDecimal afterAmount = balance.getAvailableAmount().subtract(record.getAmount());
			if (afterAmount.compareTo(BigDecimal.ZERO) < 0) {
				throw new InsufficientBalanceException("提现金额必须小于可提现余额：amount=" + info.getAmount() + "，balance=" + balance.getAvailableAmount());
			}
			balance.setAvailableAmount(afterAmount);
			balance.setFrozenAmount(balance.getFrozenAmount().add(info.getAmount()));
			balanceDao.update(balance);
		} else if (info.getStatus().getCode().equals(WithrawStatus.DONE.getCode())) {
			LiveStudioBalance balance = balanceDao.getBy(info.getLiveStudio().getId());
			BigDecimal frozenAmount = balance.getFrozenAmount().subtract(info.getAmount());
			if (frozenAmount.compareTo(BigDecimal.ZERO) < 0) {
				throw new InsufficientBalanceException("冻结金额不足以提现：amount=" + info.getAmount() + "，frozenAmount=" + balance.getAvailableAmount());
			}
			balance.setFrozenAmount(frozenAmount);
			balanceDao.update(balance);
		} else if (info.getStatus().getCode().equals(WithrawStatus.FAILED.getCode())) {
			record.setDescription(info.getDescription());

			if (record.getStatus() == WithrawStatus.NEW) {

			} else if (record.getStatus() == WithrawStatus.PROCESSING) {
				LiveStudioBalance balance = balanceDao.getBy(info.getLiveStudio().getId());
				BigDecimal frozenAmount = balance.getFrozenAmount().subtract(info.getAmount());
				if (frozenAmount.compareTo(BigDecimal.ZERO) < 0) {
					throw new InsufficientBalanceException("冻结金额不足以解冻：amount=" + info.getAmount() + "，frozenAmount=" + balance.getAvailableAmount());
				}
				balance.setFrozenAmount(frozenAmount);
				balanceDao.update(balance);
			} else {
				throw new EntityStatusException("错误的状态：stauts=" + record.getStatus());
			}
		}
		record.setStatus(BaseEnum.valueOf(WithrawStatus.class, info.getStatus().getCode()));
		dao.updateStatus(record);
	}

}
