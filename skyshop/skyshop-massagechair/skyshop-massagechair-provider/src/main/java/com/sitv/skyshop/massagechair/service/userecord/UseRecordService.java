/**
 *
 */
package com.sitv.skyshop.massagechair.service.userecord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.userecord.IUseRecordDao;
import com.sitv.skyshop.massagechair.domain.record.UseRecord;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Service
public class UseRecordService extends CrudService<IUseRecordDao, UseRecord, UseRecordInfo> implements IUseRecordService {

	public UseRecordInfo getOne(Long id) {
		return UseRecordInfo.create(get(id));
	}

	public PageInfo<UseRecordInfo> search(SearchParamInfo<UseRecordInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<UseRecord> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<UseRecord> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<UseRecordInfo> infos = UseRecordInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(UseRecordInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}

	public void createOne(UseRecordInfo t) {
		UseRecord r = new UseRecord(t.getName(), t.getDescription(), t.getFrom(), t.getChair(), t.getImei(), t.getSim(), t.getResponse(), t.getPrice());
		create(r);
	}

}
