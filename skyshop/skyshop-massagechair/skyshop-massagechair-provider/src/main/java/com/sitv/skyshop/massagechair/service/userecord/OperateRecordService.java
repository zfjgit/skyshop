/**
 *
 */
package com.sitv.skyshop.massagechair.service.userecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.userecord.IOperateRecordDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord;
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord.OperateType;
import com.sitv.skyshop.massagechair.dto.record.UserOperateRecordInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Service
public class OperateRecordService extends CrudService<IOperateRecordDao, UserOperateRecord, UserOperateRecordInfo> implements IOperateRecordService {

	@Autowired
	private IAgencyDao agencyDao;

	public UserOperateRecordInfo getOne(Long id) {
		return UserOperateRecordInfo.create(get(id));
	}

	public PageInfo<UserOperateRecordInfo> search(SearchParamInfo<UserOperateRecordInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<UserOperateRecord> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<UserOperateRecord> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<UserOperateRecordInfo> infos = UserOperateRecordInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void deleteOne(Long id) {
		throw new UnsupportedOperationException("不能删除");
	}

	public void updateOne(UserOperateRecordInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}

	public void createOne(UserOperateRecordInfo t) {
		Agency agency = null;
		if (t.getAgency() != null) {
			agency = agencyDao.get(t.getAgency().getId());
		}
		UserOperateRecord record = new UserOperateRecord(agency, t.getAccount(), t.getIp(), BaseEnum.valueOf(OperateType.class, t.getType().getCode()), t.getDescription(),
		                t.getCreateTime());
		create(record);
	}

}
