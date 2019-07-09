/**
 *
 */
package com.sitv.skyshop.massagechair.portal.ui.wx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zfj20 @ 2017年9月15日
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
public class Application extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
