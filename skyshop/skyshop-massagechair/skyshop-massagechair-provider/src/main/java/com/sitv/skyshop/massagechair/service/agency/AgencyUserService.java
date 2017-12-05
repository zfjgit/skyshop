/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.user.IUserDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.agency.AgencyUser;
import com.sitv.skyshop.massagechair.domain.user.User.UserStatus;
import com.sitv.skyshop.massagechair.domain.user.User.UserType;
import com.sitv.skyshop.massagechair.dto.agency.AgencyUserInfo;
import com.sitv.skyshop.massagechair.service.user.DefaultUserService;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Service
public class AgencyUserService extends DefaultUserService<IUserDao<AgencyUser>, AgencyUserInfo, AgencyUser> implements IAgencyUserService {

	@Autowired
	private IAgencyDao agencyDao;

	public AgencyUserInfo getOne(Long id) {
		return AgencyUserInfo.create(get(id));
	}

	public PageInfo<AgencyUserInfo> search(SearchParamInfo<AgencyUserInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<AgencyUser> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<AgencyUser> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<AgencyUserInfo> infos = AgencyUserInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(AgencyUserInfo t) {
		AgencyUser agencyUser = get(t.getId());
		agencyUser.setAgency(agencyDao.get(t.getAgency().getId()));
		agencyUser.setName(t.getName());
		agencyUser.setCode(t.getCode());
		agencyUser.setPassword(t.getPassword());
		agencyUser.setStatus(BaseEnum.valueOf(UserStatus.class, t.getStatus().getCode()));
		agencyUser.setType(BaseEnum.valueOf(UserType.class, t.getType().getCode()));
		agencyUser.setUpdateTime(Calendar.getInstance());
		agencyUser.calcCheckCode();
		update(agencyUser);
	}

	public void createOne(AgencyUserInfo t) {
		Agency agency = agencyDao.get(t.getAgency().getId());

		AgencyUser agencyUser = new AgencyUser(null, t.getCode(), t.getName(), t.getPassword(), BaseEnum.valueOf(UserStatus.class, t.getStatus().getCode()),
		                BaseEnum.valueOf(UserType.class, t.getType().getCode()), agency, DeleteStatus.NORMAL);
		agencyUser.calcCheckCode();
		create(agencyUser);
	}

}
