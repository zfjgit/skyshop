/**
 * 
 */
package com.sitv.skyshop.sync.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author zfj20 @ 2017年9月20日
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.sitv.skyshop"})
public class Application extends SpringBootServletInitializer {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
