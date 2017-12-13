/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.common.utils.image.QRCodeDrawer;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.device.IGSMModuleDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.device.IPricePackageDao;
import com.sitv.skyshop.massagechair.dao.price.IPriceDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.GSMModule.GSMModuleStatus;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.device.PricePackage;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;
import com.sitv.skyshop.service.CrudService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Slf4j
@Service("massageChairService")
public class MassageChairService extends CrudService<IMassageChairDao, MassageChair, MassageChairInfo> implements IMassageChairService {

	@Autowired
	private Environment env;

	@Autowired
	private IGSMModuleDao gsmModuleDao;

	@Autowired
	private IInstallationAddressDao installationAddressDao;

	@Autowired
	private IPriceDao<Price> priceDao;

	@Autowired
	private IPricePackageDao pricePackageDao;

	@Autowired
	private IAgencyDao agencyDao;

	@Autowired
	private IUseRecordService recordService;

	public MassageChairInfo getOne(Long id) {
		MassageChair massageChair = get(id);
		return MassageChairInfo.create(massageChair);
	}

	public PageInfo<MassageChairInfo> search(SearchParamInfo<MassageChairInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<MassageChair> massageChairs = dao.search(q);

		com.github.pagehelper.PageInfo<MassageChair> pageInfo = new com.github.pagehelper.PageInfo<>(massageChairs, 5);

		List<MassageChairInfo> massageChairInfos = MassageChairInfo.creates(pageInfo.getList());

		return new PageInfo<>(massageChairInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(MassageChairInfo t) {
		GSMModule gsmModule = gsmModuleDao.get(t.getGsmModule().getId());

		if (gsmModule.getStatus() != GSMModuleStatus.NORMAL) {
			throw new EntityStatusException("GSM模块状态不是正常状态");
		}
		if (gsmModule.getDeleteStatus() != DeleteStatus.NORMAL) {
			throw new EntityStatusException("GSM模块已删除，不能使用");
		}

		Agency agency = null;
		if (t.getAgency() != null && t.getAgency().getId() != 0) {
			agency = agencyDao.get(t.getAgency().getId());
			if (agency.getDeleteStatus() != DeleteStatus.NORMAL) {
				throw new EntityStatusException("代理商已删除，不能操作");
			}
		}

		ChairStatus chairStatus = BaseEnum.valueOf(ChairStatus.class, t.getStatus().getCode());

		InstallationAddress installationAddress = null;
		if (t.getInstallationAddress() != null) {
			installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());

			if (installationAddress.getDeleteStatus() != DeleteStatus.NORMAL) {
				throw new EntityStatusException("安装地址已删除，不能使用");
			}
		} else {
			// 选择了地址
			if (chairStatus == ChairStatus.NOTINSTALLED) {
				chairStatus = ChairStatus.NORMAL;
			}
		}

		MassageChair massageChair = get(t.getId());
		massageChair.setStatus(chairStatus);
		massageChair.setAgency(agency);
		massageChair.setBrand(t.getBrand());
		massageChair.setDescription(t.getDescription());
		massageChair.setInstallationAddress(installationAddress);
		massageChair.setGsmModule(gsmModule);
		dao.update(massageChair);

		pricePackageDao.deleteByChair(t.getId());

		if (t.getPrices() != null) {
			for (PriceInfo p : t.getPrices()) {
				Price price = priceDao.get(Long.valueOf(p.getId()));
				PricePackage pricePackage = new PricePackage(massageChair, price);
				pricePackageDao.insert(pricePackage);
			}
		}

		gsmModule.setStatus(GSMModuleStatus.USING);
		gsmModuleDao.updateStatus(gsmModule);
	}

	public void createOne(MassageChairInfo t) {
		GSMModule gsmModule = gsmModuleDao.get(t.getGsmModule().getId());
		if (gsmModule.getStatus() != GSMModuleStatus.NORMAL) {
			throw new EntityStatusException("GSM模块状态不是正常状态");
		}
		if (gsmModule.getDeleteStatus() != DeleteStatus.NORMAL) {
			throw new EntityStatusException("GSM模块已删除，不能使用");
		}

		ChairStatus chairStatus = BaseEnum.valueOf(ChairStatus.class, t.getStatus().getCode());

		InstallationAddress installationAddress = null;
		if (t.getInstallationAddress() != null && t.getInstallationAddress().getId() != 0) {
			installationAddress = installationAddressDao.get(t.getInstallationAddress().getId());
			if (installationAddress.getDeleteStatus() != DeleteStatus.NORMAL) {
				throw new EntityStatusException("安装地址已删除，不能使用");
			}
		} else {
			// 选择了地址
			if (chairStatus == ChairStatus.NOTINSTALLED) {
				chairStatus = ChairStatus.NORMAL;
			}
		}
		Agency agency = null;
		if (t.getAgency() != null && t.getAgency().getId() != 0) {
			agency = agencyDao.get(t.getAgency().getId());
			if (agency.getDeleteStatus() != DeleteStatus.NORMAL) {
				throw new EntityStatusException("代理商已删除，不能操作");
			}
		}

		MassageChair massageChair = new MassageChair(null, t.getName(), t.getDescription(), t.getBrand(), chairStatus, gsmModule, installationAddress, agency, DeleteStatus.NORMAL,
		                "");

		dao.insert(massageChair);

		if (t.getPrices() != null) {
			for (PriceInfo p : t.getPrices()) {
				Price price = priceDao.get(Long.valueOf(p.getId()));
				PricePackage pricePackage = new PricePackage(massageChair, price);
				pricePackageDao.insert(pricePackage);
			}
		}

		massageChair.setQrcode(drawQRCode(massageChair.getId().toString()));
		dao.updateQRCodeUrl(massageChair);

		gsmModule.setStatus(GSMModuleStatus.USING);
		gsmModuleDao.updateStatus(gsmModule);
	}

	public MassageChairInfo getByIMEI(String imei) {
		return MassageChairInfo.create(dao.getByIMEI(imei));
	}

	public void deleteOne(Long id) {
		MassageChairInfo chairInfo = new MassageChairInfo();
		chairInfo.setId(id);
		chairInfo.setDeleteStatus(new EnumInfo<>(DeleteStatus.DELETED));
		updateDeleteStatus(chairInfo);
	}

	public void updateDeleteStatus(MassageChairInfo t) {
		MassageChair chair = get(t.getId());
		chair.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		dao.updateDeleteStatus(chair);
	}

	private String drawQRCode(String sim) {
		String filePath = env.getProperty(Constants.IMG_FILE_SAVE_DIR_QRCODE) + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_4) + Constants.IMG_JPG;
		String imgUrl = env.getProperty(Constants.IMG_SERVER_URL) + filePath;
		String fileAbsPathName = env.getProperty(Constants.IMG_FILE_SAVE_SERVER_ROOT) + filePath;

		// 生成二维码
		String url = env.getProperty(Constants.CHAIR_SCAN_UI_URL) + sim;
		QRCodeDrawer.drawQRCode(url, fileAbsPathName);

		return imgUrl;
	}

	public OperateResultInfo checkServiceStatus(Long id) {
		log.debug("同步检测设备>>>");
		MassageChair chair = get(id);
		if (chair == null) {
			throw new EntityNotFoundException(String.format("没有找到ID为%s的设备", id));
		}
		if (chair.getStatus() == MassageChair.ChairStatus.FAULT) {
			throw new EntityStatusException("设备处于故障状态，不能进行操作");
		}
		String sim = chair.getGsmModule().getSimCard().getSim();

		OperateResultInfo operateResultInfo = recordService.getCheckOperateResult(sim, 3);
		log.debug("operateResultInfo=" + operateResultInfo);
		if (operateResultInfo == null) {
			UseRecordInfo recordInfo = new UseRecordInfo(null, null, "", EnumInfo.valueOf(UseRecordType.class, UseRecordType.CHECK.getCode()), "管理后台", "发送检测指令",
			                chair.getGsmModule().getImei(), sim, "", "", chair.getName(), "", "", chair.getInstallationAddress().getFullAddress(), "", Calendar.getInstance());
			recordService.createCheckRecord(recordInfo);

			operateResultInfo = recordService.getCheckOperateResult(sim, 3);
			log.debug("operateResultInfo=" + operateResultInfo);
		}
		return operateResultInfo;
	}

	public void asyncCheckServiceStatus(Long id) {
		log.debug("异步检测设备>>>");
		MassageChair chair = get(id);
		if (chair == null) {
			throw new EntityNotFoundException(String.format("没有找到ID为%s的设备", id));
		}
		if (chair.getStatus() == MassageChair.ChairStatus.FAULT) {
			throw new EntityStatusException("设备处于故障状态，不能进行操作");
		}
		String sim = chair.getGsmModule().getSimCard().getSim();

		OperateResultInfo operateResultInfo = recordService.getCheckOperateResult(sim, 0);
		log.debug("operateResultInfo=" + operateResultInfo);
		String operateFail = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_FAIL);
		if (operateResultInfo == null || operateFail.equals(operateResultInfo.getCode())) {
			UseRecordInfo recordInfo = new UseRecordInfo(null, null, "", EnumInfo.valueOf(UseRecordType.class, UseRecordType.CHECK.getCode()), "扫码使用", "发送检测指令",
			                chair.getGsmModule().getImei(), sim, "", "", chair.getName(), "", "", chair.getInstallationAddress().getFullAddress(), "", Calendar.getInstance());
			recordService.createAsyncCheckRecord(recordInfo);
		}
	}

	public void updateStatus(MassageChairInfo chairInfo) {
		MassageChair massageChair = get(chairInfo.getId());
		massageChair.setStatus(BaseEnum.valueOf(ChairStatus.class, chairInfo.getStatus().getCode()));
		dao.updateStatus(massageChair);
	}
}
