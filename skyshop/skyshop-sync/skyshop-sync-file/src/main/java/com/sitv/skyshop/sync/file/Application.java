/**
 *
 */
package com.sitv.skyshop.sync.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zfj20 @ 2017年9月15日
 */
@Configuration
@ServletComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.sitv.skyshop")
public class Application extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
