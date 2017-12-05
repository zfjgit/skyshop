/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.Agency.AgencyLevel;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class AgencyService extends CrudService<IAgencyDao, Agency, AgencyInfo> implements IAgencyService {

	public AgencyInfo getOne(Long id) {
		return AgencyInfo.create(get(id));
	}

	public PageInfo<AgencyInfo> search(SearchParamInfo<AgencyInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Agency> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<Agency> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyInfo> infos = AgencyInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(AgencyInfo t) {
		Agency agency = get(t.getId());
		agency.setBalance(t.getBalance());
		agency.setName(t.getName());
		agency.setLevel(BaseEnum.valueOf(AgencyLevel.class, t.getLevel()));
		agency.setOrderIncomePercentage(t.getOrderIncomePercentage());
		agency.setParent(get(t.getParent().getId()));
		agency.setUpdateTime(Calendar.getInstance());
		agency.calcCheckCode();
		update(agency);
	}

	public void createOne(AgencyInfo t) {
		Agency parent = get(t.getParent().getId());
		Agency agency = new Agency(null, t.getName(), parent, BaseEnum.valueOf(AgencyLevel.class, t.getLevel()), t.getOrderIncomePercentage(), t.getBalance());
		agency.calcCheckCode();
		create(agency);
	}

	public void updateDeleteStatus(AgencyInfo t) {
		Agency agency = get(t.getId());
		agency.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus()));
		agency.calcCheckCode();

		dao.updateDeleteStatus(agency);
	}

}
