/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.tbataobao.dto.ShopIndustryInfo;

/**
 * @author zfj20
 */
public interface IShopIndustryService extends IBaseService<ShopIndustryInfo> {

	List<ShopIndustryInfo> findAll();
}
