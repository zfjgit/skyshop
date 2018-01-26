/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.course;

import java.io.File;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.CourseResourseInfo;
import com.sitv.skyshop.wisdomedu.service.course.ICourseResourceService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@Api("课程素材接口")
@Validated
@RestController
@RequestMapping("/courseresource")
public class CourseResourceController extends BaseRestController<ICourseResourceService, CourseResourseInfo> {

	@Value("${file.upload.savepath}")
	private String fileSavePath;

	@Value("${file.server.path}")
	private String fileServerPath;

	@Value("${file.upload.course.materials.dir}")
	private String courseMaterialsDir;

	@PostMapping("/uploadcreate")
	public ResponseInfo<CourseResourseInfo> uploadCreate(@Valid @NotNull @ModelAttribute CourseResourseInfo info,
	                @NotNull @RequestParam(value = "file", required = false) MultipartFile file) {

		// MultipartFile file = multipartRequest.getFile("file");
		if (file == null) {
			if (!Utils.isNull(info.getContent())) {
				service.createOne(info);
				return ResponseInfo.SUCCESS(info);
			} else {
				return ResponseInfo.ARGS_ERROR("素材必须包含文字或文件");
			}
		}

		String originalFileName = file.getOriginalFilename();
		String suffix = "";
		if (originalFileName.lastIndexOf(".") > 0) {
			suffix = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
		}
		String fileName = "livestudio_logo_" + System.currentTimeMillis() + suffix;

		String savePath = fileSavePath + courseMaterialsDir + fileName;
		log.debug("svepath=" + savePath);

		File dest = new File(savePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		String url = fileServerPath + courseMaterialsDir + fileName;
		log.debug("url=" + url);
		try {
			file.transferTo(dest);

			info.setLink(url);
			info.setSeconds(0);
			info.setSize(Long.valueOf(file.getSize() / (1024 * 1024)).intValue());
			info.setName(originalFileName);
			service.createOne(info);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return ResponseInfo.SUCCESS(info);
	}

}
