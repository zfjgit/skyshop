/**
 *
 */
package com.sitv.skyshop.wisdomedu;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * @author zfj20 @ 2018年3月26日
 */
@Configuration
@PropertySource("classpath:config/kaptcha.properties")
public class KaptchaConfig {

	@Bean
	public DefaultKaptcha getDefaultKaptcha(@Autowired Environment env) {
		com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", env.getProperty("kaptcha.border"));
		properties.setProperty("kaptcha.border.color", env.getProperty("kaptcha.border.color"));
		properties.setProperty("kaptcha.textproducer.font.color", env.getProperty("kaptcha.textproducer.font.color"));
		properties.setProperty("kaptcha.image.width", env.getProperty("kaptcha.image.width"));
		properties.setProperty("kaptcha.image.height", env.getProperty("kaptcha.image.height"));
		properties.setProperty("kaptcha.textproducer.font.size", env.getProperty("kaptcha.textproducer.font.size"));
		properties.setProperty("kaptcha.session.key", env.getProperty("kaptcha.session.key"));
		properties.setProperty("kaptcha.textproducer.char.length", env.getProperty("kaptcha.textproducer.char.length"));
		properties.setProperty("kaptcha.textproducer.font.names", env.getProperty("kaptcha.textproducer.font.names"));
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);

		return defaultKaptcha;
	}
}
