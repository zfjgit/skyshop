/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.ISIMCardDao;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.GSMModule.GSMModuleStatus;
import com.sitv.skyshop.massagechair.domain.device.SIMCard;
import com.sitv.skyshop.massagechair.dto.device.GSMModuleInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class GSMModuleService extends CrudService<IGSMModuleDao, GSMModule, GSMModuleInfo> implements IGSMModuleService {

	@Autowired
	private ISIMCardDao simCardDao;

	public GSMModuleInfo getOne(Long id) {
		return GSMModuleInfo.create(get(id));
	}

	public PageInfo<GSMModuleInfo> search(SearchParamInfo<GSMModuleInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<GSMModule> gsmModules = dao.search(q);

		com.github.pagehelper.PageInfo<GSMModule> pageInfo = new com.github.pagehelper.PageInfo<>(gsmModules, 5);

		List<GSMModuleInfo> gsmModuleInfos = GSMModuleInfo.creates(pageInfo.getList());

		return new PageInfo<>(gsmModuleInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(GSMModuleInfo t) {
		SIMCard simCard = simCardDao.get(t.getSimCard().getId());

		GSMModule gsmModule = get(t.getId());
		gsmModule.setDescription(t.getDescription());
		gsmModule.setImei(t.getImei());
		gsmModule.setModule(t.getModule());
		gsmModule.setSimCard(simCard);
		gsmModule.setStatus(GSMModuleStatus.valueOf(t.getStatus()));
		gsmModule.setUpdateTime(Calendar.getInstance());

		update(gsmModule);
	}

	public void createOne(GSMModuleInfo t) {
		SIMCard simCard = simCardDao.get(t.getSimCard().getId());
		GSMModule gsmModule = new GSMModule(null, t.getImei(), t.getModule(), GSMModuleStatus.valueOf(t.getStatus()), simCard, t.getDescription(), Calendar.getInstance(), null);
		create(gsmModule);
	}

	public void updateDeleteStatus(GSMModuleInfo t) {
		GSMModule gsmModule = get(t.getId());
		gsmModule.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus()));
		dao.updateDeleteStatus(gsmModule);
	}

}
