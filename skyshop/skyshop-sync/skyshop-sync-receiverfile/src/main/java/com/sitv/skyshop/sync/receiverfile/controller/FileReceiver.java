package com.sitv.skyshop.sync.receiverfile.controller;

import java.io.File;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servlet implementation class DataReceiver
 */
@RestController
@RequestMapping("/file")
public class FileReceiver {

	private static final Logger log = LoggerFactory.getLogger(FileReceiver.class);

	@Autowired
	private Environment env;

	@RequestMapping(value = "/receiv", method = RequestMethod.POST)
	public String receiv(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
		try {
			log.info("fileName=" + fileName);

			// String rawEncoding = env.getProperty("file.name.raw.encoding");
			// String localEncoding = env.getProperty("file.name.local.encoding");

			// log.info("file.name.raw.encoding=" + rawEncoding);
			// log.info("file.name.local.encoding=" + localEncoding);

			// path = new String(path.getBytes(rawEncoding), localEncoding);
			fileName = URLDecoder.decode(fileName, "utf-8");
			log.info("文件名=" + fileName);

			File newFile = new File(env.getProperty("file.save.dir") + fileName);
			file.transferTo(newFile);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "ok";
	}

}
