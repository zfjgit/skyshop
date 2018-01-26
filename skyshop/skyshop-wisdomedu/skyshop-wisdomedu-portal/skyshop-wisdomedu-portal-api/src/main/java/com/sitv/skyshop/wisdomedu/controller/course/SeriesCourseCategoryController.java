/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.course;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseCategoryInfo;
import com.sitv.skyshop.wisdomedu.service.course.ISeriesCourseCategoryService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("系列课分类接口")
@Validated
@RestController
@RequestMapping("/seriescoursecategory")
public class SeriesCourseCategoryController extends BaseRestController<ISeriesCourseCategoryService, SeriesCourseCategoryInfo> {

	@PutMapping("/sort/")
	public ResponseInfo<String> sort(@NotEmpty @RequestParam("ids[]") String[] ids) {
		service.sort(ids);
		return ResponseInfo.SUCCESS("");
	}
}
