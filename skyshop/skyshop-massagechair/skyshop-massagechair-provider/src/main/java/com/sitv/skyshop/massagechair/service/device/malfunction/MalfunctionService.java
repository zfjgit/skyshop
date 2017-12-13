/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.device.malfunction.IMalfunctionDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionType;
import com.sitv.skyshop.massagechair.dto.device.malfunction.MalfunctionInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Service
public class MalfunctionService extends CrudService<IMalfunctionDao<Malfunction>, Malfunction, MalfunctionInfo> implements IMalfunctionService<MalfunctionInfo> {

	@Autowired
	private IMassageChairDao deviceDao;

	public MalfunctionInfo getOne(Long id) {
		return MalfunctionInfo.create(get(id));
	}

	public PageInfo<MalfunctionInfo> search(SearchParamInfo<MalfunctionInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Malfunction> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<Malfunction> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<MalfunctionInfo> infos = MalfunctionInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(MalfunctionInfo t) {
		Malfunction m = get(t.getId());
		m.setStatus(BaseEnum.valueOf(MalfunctionStatus.class, t.getStatus().getCode()));
		update(m);

		if (m.getStatus() == MalfunctionStatus.PROCESSED) {
			MassageChair chair = m.getChair();
			if (chair.getStatus() == ChairStatus.FAULT) {
				chair.setStatus(ChairStatus.NORMAL);
			}
			deviceDao.updateStatus(chair);
		}
	}

	public void createOne(MalfunctionInfo t) {
		MassageChair massageChair = deviceDao.get(t.getChair().getId());

		Malfunction malfunction = new Malfunction(null, massageChair, BaseEnum.valueOf(MalfunctionType.class, t.getType().getCode()),
		                BaseEnum.valueOf(MalfunctionStatus.class, t.getStatus().getCode()), t.getDescription(), Calendar.getInstance(), Calendar.getInstance());

		create(malfunction);

		massageChair.setStatus(ChairStatus.FAULT);
		deviceDao.updateStatus(massageChair);
	}

}
