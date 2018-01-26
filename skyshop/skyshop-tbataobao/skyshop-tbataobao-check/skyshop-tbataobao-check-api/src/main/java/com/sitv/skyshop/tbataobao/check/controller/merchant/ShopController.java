/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller.merchant;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.tbataobao.dto.ShopAddressInfo;
import com.sitv.skyshop.tbataobao.dto.ShopIndustryInfo;
import com.sitv.skyshop.tbataobao.service.IShopAddressService;
import com.sitv.skyshop.tbataobao.service.IShopIndustryService;
import com.sitv.skyshop.tbataobao.service.IShopService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@CrossOrigin
@Api("店鋪信息接口")
@Validated
@RestController("merchantShopController")
@RequestMapping("/merchant/shop")
public class ShopController {

	@Autowired
	private IShopService shopService;

	@Autowired
	private IShopIndustryService industryService;

	@Autowired
	private IShopAddressService addressService;

	@Value("${file.upload.savepath}")
	private String fileSavePath;

	@Value("${file.upload.shop.logo.dir}")
	private String shopLogoDir;

	@Value("${file.upload.shop.banner.dir}")
	private String shopBannerDir;

	@Value("${img.server.path}")
	private String imgServerPath;

	@PutMapping(value = "/")
	public ResponseInfo<String> update(@NotNull @RequestParam Map<String, Object> data) {
		if (Utils.isNull(data.get("id"))) {
			return ResponseInfo.ARGS_ERROR("ID不能为空");
		}
		// Object img1 = data.get("img1");
		// log.debug("img1=" + img1);
		shopService.update(data);

		return ResponseInfo.UPDATED_SUCCESS("");
	}

	@GetMapping("/{id}")
	public ResponseInfo<Map<String, Object>> get(@PathVariable @Min(0) String id) {
		log.debug("id=" + id);

		Map<String, Object> shop = shopService.get(Long.valueOf(id));

		List<ShopAddressInfo> provinces = addressService.findChildrens(0l);
		shop.put("provinces", provinces);

		if (!Utils.isNull(shop.get("provinceid"))) {
			Long provinceId = Long.valueOf(shop.get("provinceid") + "");
			List<ShopAddressInfo> citys = addressService.findChildrens(provinceId);
			shop.put("citys", citys);
		}

		if (!Utils.isNull(shop.get("cityid"))) {
			Long cityId = Long.valueOf(shop.get("cityid") + "");
			List<ShopAddressInfo> districts = addressService.findChildrens(cityId);
			shop.put("districts", districts);
		}

		List<ShopIndustryInfo> industrys = industryService.findAll();

		shop.put("industrys", industrys);

		return ResponseInfo.SUCCESS(shop);
	}

	@PostMapping("/logoupload")
	public ResponseInfo<String> logoupload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		log.debug("file=" + file.getOriginalFilename());

		String suffix = fileName.substring(fileName.lastIndexOf("."));
		log.debug("suffix=" + suffix);

		String newFileName = "shoplogo_" + System.currentTimeMillis() + suffix;
		String newFilePath = shopLogoDir + newFileName;
		log.debug("newFilePath=" + newFilePath);

		File dest = new File(fileSavePath + newFilePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			file.transferTo(dest);
		} catch (Exception e) {
			return ResponseInfo.UPLOAD_ERROR(e.getMessage());
		}
		return ResponseInfo.SUCCESS(newFilePath);
	}

	@PostMapping("/bannerupload")
	public ResponseInfo<String> bannerupload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		log.debug("file=" + file.getOriginalFilename());

		String suffix = fileName.substring(fileName.lastIndexOf("."));
		log.debug("suffix=" + suffix);

		String newFileName = "shopbanner_" + System.currentTimeMillis() + suffix;
		String newFilePath = shopBannerDir + newFileName;
		log.debug("newFilePath=" + newFilePath);

		File dest = new File(fileSavePath + newFilePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			file.transferTo(dest);
		} catch (Exception e) {
			return ResponseInfo.UPLOAD_ERROR(e.getMessage());
		}
		return ResponseInfo.SUCCESS(newFilePath);
	}
}
