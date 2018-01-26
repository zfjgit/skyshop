/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller.merchant;

import java.io.File;
import java.util.HashMap;
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
import com.sitv.skyshop.tbataobao.service.IProductCategoryService;
import com.sitv.skyshop.tbataobao.service.IProductService;
import com.sitv.skyshop.tbataobao.service.ISysCategoryService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@CrossOrigin
@Api("商品信息接口")
@Validated
@RestController("merchantProductController")
@RequestMapping("/merchant/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ISysCategoryService sysCategoryService;

	@Autowired
	private IProductCategoryService productCategoryService;

	@Value("${file.upload.savepath}")
	private String fileSavePath;

	@Value("${file.upload.product.img.dir}")
	private String productImgDir;

	@Value("${file.upload.product.desc.img.dir}")
	private String productIntroImgDir;

	@Value("${img.server.path}")
	private String imgServerPath;

	@PutMapping(value = "/")
	public ResponseInfo<String> update(@NotNull @RequestParam Map<String, Object> data) {
		if (Utils.isNull(data.get("id"))) {
			return ResponseInfo.ARGS_ERROR("ID不能为空");
		}
		Object img1 = data.get("img1");
		log.debug("img1=" + img1);
		productService.update(data);
		return ResponseInfo.UPDATED_SUCCESS("");
	}

	@GetMapping("/{id}")
	public ResponseInfo<Map<String, Object>> get(@PathVariable @Min(0) String id) {
		log.debug("id=" + id);

		Map<String, Object> product = productService.get(Long.valueOf(id));

		if (product != null && product.size() > 0) {
			List<Map<String, Object>> productCategorys = productCategoryService.findParents(Long.valueOf(product.get("categoryIdx1") + ""));
			List<Map<String, Object>> sysCateogrys = sysCategoryService.findParents(Long.valueOf(product.get("sysCatagoryId") + ""));

			product.put("productCategorys", productCategorys);
			product.put("sysCateogrys", sysCateogrys);
		}

		return ResponseInfo.SUCCESS(product);
	}

	@PostMapping("/imgupload")
	public ResponseInfo<String> imgupload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		log.debug("file=" + file.getOriginalFilename());

		String suffix = fileName.substring(fileName.lastIndexOf("."));
		log.debug("suffix=" + suffix);

		String newFileName = "productimg_" + System.currentTimeMillis() + suffix;
		String newFilePath = productImgDir + newFileName;
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

	@PostMapping("/introimgupload")
	public Map<String, Object> introimgupload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		log.debug("file=" + file.getOriginalFilename());

		String suffix = fileName.substring(fileName.lastIndexOf("."));
		log.debug("suffix=" + suffix);

		String newFileName = "productdesc_" + System.currentTimeMillis() + suffix;
		String newFilePath = productIntroImgDir + newFileName;
		log.debug("newFilePath=" + newFilePath);

		File dest = new File(fileSavePath + newFilePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		Map<String, Object> result = new HashMap<>();
		try {
			file.transferTo(dest);
			result.put("error", 0);
			result.put("url", imgServerPath + newFilePath);
		} catch (Exception e) {
			result.put("error", 1);
			result.put("message", e.getMessage());
		}
		return result;
	}
}
