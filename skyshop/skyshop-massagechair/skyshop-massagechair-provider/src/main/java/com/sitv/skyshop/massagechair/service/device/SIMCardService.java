/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.ISIMCardDao;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.SIMCard;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardOperator;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardStatus;
import com.sitv.skyshop.massagechair.dto.device.SIMCardInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class SIMCardService extends CrudService<ISIMCardDao, SIMCard, SIMCardInfo> implements ISIMCardService {

	@Autowired
	private IGSMModuleDao moduleDao;

	public SIMCardInfo getOne(Long id) {
		return SIMCardInfo.create(get(id));
	}

	public PageInfo<SIMCardInfo> search(SearchParamInfo<SIMCardInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SIMCard> simCards = dao.search(q);

		com.github.pagehelper.PageInfo<SIMCard> pageInfo = new com.github.pagehelper.PageInfo<>(simCards, 5);

		List<SIMCardInfo> simCardOperators = SIMCardInfo.creates(pageInfo.getList());

		return new PageInfo<>(simCardOperators, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SIMCardInfo t) {
		SIMCard simCard = get(t.getId());

		SIMCardStatus status = BaseEnum.valueOf(SIMCardStatus.class, t.getStatus().getCode());
		if (simCard.getStatus() != status) {
			GSMModule gsmModule = moduleDao.getBySIM(t.getId());
			if (gsmModule != null) {
				throw new EntityStatusException("SIM卡正在使用中，不能修改状态");
			}
		}
		simCard.setStatus(status);
		simCard.setSim(t.getSim());
		simCard.setName(t.getSim());
		simCard.setDueDate(t.getDueDate());
		simCard.setDataFlow(t.getDataFlow());
		simCard.setDescription(t.getDescription());
		simCard.setOperator(BaseEnum.valueOf(SIMCardOperator.class, t.getOperator().getCode()));
		simCard.setUpdateTime(Calendar.getInstance());

		update(simCard);
	}

	public void createOne(SIMCardInfo t) {
		SIMCard simCard = new SIMCard(null, t.getDescription(), t.getDataFlow(), t.getDueDate(), t.getSim(), BaseEnum.valueOf(SIMCardOperator.class, t.getOperator().getCode()),
		                BaseEnum.valueOf(SIMCardStatus.class, t.getStatus().getCode()), DeleteStatus.NORMAL);
		create(simCard);
	}

	public void updateDeleteStatus(SIMCardInfo t) {
		SIMCard simCard = get(t.getId());
		simCard.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		dao.updateDeleteStatus(simCard);
	}

}
