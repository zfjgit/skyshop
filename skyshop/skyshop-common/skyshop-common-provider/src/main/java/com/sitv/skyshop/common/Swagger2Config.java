package com.sitv.skyshop.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sitv.skyshop.dto.SearchParamInfo;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(SearchParamInfo.class).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.sitv.skyshop")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("skyshop cloud api").description("skyshop cloud api").termsOfServiceUrl("http://www.tba-taobao.com/")
				.contact(new Contact("skyshop", "http://www.tba-taobao.com/", "zfjemail@qq.com")).version("1.0.0-SNAPSHOT").build();
	}
}