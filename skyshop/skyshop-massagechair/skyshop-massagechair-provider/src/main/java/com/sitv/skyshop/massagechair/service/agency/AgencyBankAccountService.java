/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyBankAccountDao;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.AgencyBankAccount;
import com.sitv.skyshop.massagechair.dto.agency.AgencyBankAccountInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class AgencyBankAccountService extends CrudService<IAgencyBankAccountDao, AgencyBankAccount, AgencyBankAccountInfo> implements IAgencyBankAccountService {

	@Autowired
	private IAgencyDao agencyDao;

	public AgencyBankAccountInfo getOne(Long id) {
		return AgencyBankAccountInfo.create(get(id));
	}

	public PageInfo<AgencyBankAccountInfo> search(SearchParamInfo<AgencyBankAccountInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<AgencyBankAccount> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<AgencyBankAccount> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyBankAccountInfo> infos = AgencyBankAccountInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(AgencyBankAccountInfo t) {
		AgencyBankAccount account = get(t.getId());
		account.setBank(t.getBank());
		account.setAccount(t.getAccount());
		account.setAccountName(t.getAccountName());
		account.setAgency(agencyDao.get(t.getAgency().getId()));
		account.setUpdateTime(Calendar.getInstance());
		account.calcCheckCode();
		update(account);
	}

	public void createOne(AgencyBankAccountInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());
		AgencyBankAccount account = new AgencyBankAccount(agency, t.getBank(), t.getAccount(), t.getAccountName());
		account.calcCheckCode();
		create(account);
	}

}
