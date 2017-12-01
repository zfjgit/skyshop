/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import com.sitv.skyshop.massagechair.dto.agency.AgencyUserInfo;
import com.sitv.skyshop.massagechair.service.user.IUserService;

/**
 * @author zfj20 @ 2017年12月5日
 */
public interface IAgencyUserService extends IUserService<AgencyUserInfo> {

	AgencyUserInfo login(String code, String pwd);

}
