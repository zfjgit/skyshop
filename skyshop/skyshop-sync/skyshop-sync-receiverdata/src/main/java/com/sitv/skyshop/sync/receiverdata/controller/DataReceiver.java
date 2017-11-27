package com.sitv.skyshop.sync.receiverdata.controller;

import static com.sitv.skyshop.sync.receiverdata.service.IUpdateService.ERROR;
import static com.sitv.skyshop.sync.receiverdata.service.IUpdateService.OK;

import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.sync.receiverdata.service.IUpdateService;

/**
 * Servlet implementation class DataReceiver
 */
@RestController
@RequestMapping("/data")
public class DataReceiver {

	private static final Logger log = LoggerFactory.getLogger(DataReceiver.class);

	// @Autowired
	// private Environment env;

	@Autowired
	@Qualifier("mysqlService")
	private IUpdateService mysqlService;

	@Autowired
	@Qualifier("sqlserverService")
	private IUpdateService sqlserverService;

	@RequestMapping(value = "/receiv", method = RequestMethod.POST)
	public String receiv(@RequestParam("sql") String sqlObjectString) {
		try {
			log.info("sqlObjectString=" + sqlObjectString);

			if (StringUtils.isBlank(sqlObjectString)) {
				return OK;
			}

			// String rawEncoding = env.getProperty("data.raw.encoding");
			// String localEncoding = env.getProperty("data.local.encoding");

			// log.info("data.raw.encoding=" + rawEncoding);
			// log.info("data.local.encoding=" + localEncoding);

			// sqlObjectString = new String(sqlObjectString.getBytes(rawEncoding), localEncoding);

			sqlObjectString = URLDecoder.decode(sqlObjectString, "UTF-8");

			log.info("sqlObjectString=" + sqlObjectString);

			JSONObject sqlObject = new JSONObject(sqlObjectString);

			mysqlService.update(sqlObject);

			// sqlserverService.update(sqlObject);

			return OK;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ERROR;
		}
	}
}
