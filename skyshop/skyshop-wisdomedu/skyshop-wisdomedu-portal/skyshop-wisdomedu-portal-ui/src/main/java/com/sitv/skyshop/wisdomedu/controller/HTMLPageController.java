/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zfj20
 */
@Controller
public class HTMLPageController {

	@RequestMapping(value = { "{file}.html", "/{dir}/{file}.html" })
	public String html(@PathVariable String dir, @PathVariable String file) {
		if (dir == null) {
			return file;
		}
		return dir + "/" + file;
	}
}
