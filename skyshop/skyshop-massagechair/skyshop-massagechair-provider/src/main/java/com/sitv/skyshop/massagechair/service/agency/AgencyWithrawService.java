/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.domain.Withraw.WithrawStatus;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyWithrawDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.AgencyWithraw;
import com.sitv.skyshop.massagechair.dto.agency.AgencyWithrawInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class AgencyWithrawService extends CrudService<IAgencyWithrawDao, AgencyWithraw, AgencyWithrawInfo> implements IAgencyWithrawService {

	@Autowired
	private IAgencyDao agencyDao;

	public AgencyWithrawInfo getOne(Long id) {
		return AgencyWithrawInfo.create(get(id));
	}

	public PageInfo<AgencyWithrawInfo> search(SearchParamInfo<AgencyWithrawInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<AgencyWithraw> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<AgencyWithraw> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyWithrawInfo> infos = AgencyWithrawInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(AgencyWithrawInfo t) {
		AgencyWithraw withraw = get(t.getId());
		withraw.setBank(t.getBank());
		withraw.setAccount(t.getAccount());
		withraw.setAccountName(t.getAccountName());
		withraw.setAgency(agencyDao.get(t.getAgency().getId()));
		withraw.setMoney(t.getMoney());
		withraw.setStatus(BaseEnum.valueOf(WithrawStatus.class, t.getStatus().getCode()));
		withraw.setUpdateTime(Calendar.getInstance());
		withraw.calcCheckCode();
		update(withraw);
	}

	public void createOne(AgencyWithrawInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());
		AgencyWithraw withraw = new AgencyWithraw(agency, t.getMoney(), BaseEnum.valueOf(WithrawStatus.class, t.getStatus().getCode()), t.getBank(), t.getAccount(),
		                t.getAccountName());
		withraw.calcCheckCode();
		create(withraw);
	}

}
