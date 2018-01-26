/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.domain.Withraw.WithrawStatus;
import com.sitv.skyshop.common.exception.InsufficientBalanceException;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.common.utils.httpclient4.HttpConnectionManager;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyWithrawDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.AgencyWithraw;
import com.sitv.skyshop.massagechair.dto.agency.AgencyWithrawInfo;
import com.sitv.skyshop.service.CrudService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Slf4j
@Service
public class AgencyWithrawService extends CrudService<IAgencyWithrawDao, AgencyWithraw, AgencyWithrawInfo> implements IAgencyWithrawService {

	@Autowired
	private IAgencyDao agencyDao;

	@Autowired
	private Environment env;

	@Autowired
	private HttpConnectionManager httpConnectionManager;

	public AgencyWithrawInfo getOne(Long id) {
		return AgencyWithrawInfo.create(get(id));
	}

	public PageInfo<AgencyWithrawInfo> search(SearchParamInfo<AgencyWithrawInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<AgencyWithraw> entitys = dao.search(q);

		BigDecimal totalMoney = dao.getWithrawTotalMoney(q);

		com.github.pagehelper.PageInfo<AgencyWithraw> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyWithrawInfo> infos = AgencyWithrawInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), totalMoney);
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
		if (t.getMoney().compareTo(agency.getBalance()) > 0) {
			throw new InsufficientBalanceException("余额不足以完成本次提现");
		}

		log.debug("调用提现接口>>>");
		String host = env.getProperty(Constants.ORDER_SERVICE_PARTITION_SERVER_URL);
		String params = "?type=Withdrawals&user3_sno=" + t.getAgency().getId() + "&record_money=" + t.getMoney();

		String result = httpConnectionManager.doHttpGet(host + params);

		log.debug("result=" + result);
		if (!Utils.isNull(result)) {
			JSONObject json = new JSONObject(result);
			if (json.getInt("status") == 1) {
				log.debug("调用提现接口成功");
				return;
			}
		}
		throw new RuntimeException("提现失败");
	}

}
