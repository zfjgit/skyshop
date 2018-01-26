/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

	@Value("${file.upload.savepath}")
	private String fileSavePath;

	@Value("${file.server.path}")
	private String fileServerPath;

	@Value("${file.upload.livestudio.logo.dir}")
	private String liveStudioLogoDir;

	@Value("${file.upload.livestudio.bg.dir}")
	private String liveStudioBgDir;

	@Value("${file.upload.livestudio.adv.dir}")
	private String liveStudioAdvDir;

	@Value("${file.upload.course.bg.dir}")
	private String liveStudioCourseBgDir;

	@PostMapping("/livestudio/logo")
	public Map<String, Object> uploadLiveStudioLogo(MultipartRequest multipartRequest) {
		Map<String, Object> r = new HashMap<>();
		r.put("code", 1);

		MultipartFile file = multipartRequest.getFile("file");
		if (file == null) {
			r.put("code", 0);
			return r;
		}

		String originalFileName = file.getOriginalFilename();
		String fileName = "livestudio_logo_" + System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));

		String savePath = fileSavePath + liveStudioLogoDir + fileName;
		log.debug("svepath=" + savePath);

		File dest = new File(savePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		String url = fileServerPath + liveStudioLogoDir + fileName;
		log.debug("url=" + url);
		try {
			file.transferTo(dest);
			r.put("url", url);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			r.put("code", 0);
			r.put("msg", e.getMessage());
		}

		return r;
	}

	@PostMapping("/livestudio/bg")
	public Map<String, Object> uploadLiveStudioBg(MultipartRequest multipartRequest) {
		Map<String, Object> r = new HashMap<>();
		r.put("code", 1);

		MultipartFile file = multipartRequest.getFile("file");
		if (file == null) {
			r.put("code", 0);
			return r;
		}

		String originalFileName = file.getOriginalFilename();
		String fileName = "livestudio_bg_" + System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));

		String savePath = fileSavePath + liveStudioBgDir + fileName;
		log.debug("svepath=" + savePath);

		File dest = new File(savePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		String url = fileServerPath + liveStudioBgDir + fileName;
		log.debug("url=" + url);
		try {
			file.transferTo(dest);
			r.put("url", url);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			r.put("code", 0);
			r.put("msg", e.getMessage());
		}

		return r;
	}

	@PostMapping("/course/bg")
	public Map<String, Object> uploadCourseBg(MultipartRequest multipartRequest) {
		Map<String, Object> r = new HashMap<>();
		r.put("code", 1);

		MultipartFile file = multipartRequest.getFile("file");
		if (file == null) {
			r.put("code", 0);
			return r;
		}

		String originalFileName = file.getOriginalFilename();
		String fileName = "course_bg_" + System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));

		String savePath = fileSavePath + liveStudioCourseBgDir + fileName;
		log.debug("svepath=" + savePath);

		File dest = new File(savePath);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		String url = fileServerPath + liveStudioCourseBgDir + fileName;
		log.debug("url=" + url);
		try {
			file.transferTo(dest);
			r.put("url", url);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			r.put("code", 0);
			r.put("msg", e.getMessage());
		}

		return r;
	}
}
