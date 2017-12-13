package com.sitv.skyshop.massagechair.portal.api.wx.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sitv.skyshop.common.utils.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name(Constants.TOKEN_HEADER).defaultValue("***").description("token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
		List<Parameter> params = new ArrayList<>();
		params.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes().apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.sitv.skyshop"))
		                .paths(PathSelectors.any()).build().globalOperationParameters(params);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("chairs-api-wxadmin").description("skyshop massagechair wxadmin api")
		                .termsOfServiceUrl("https://github.com/zfjgit/skyshop/tree/master/skyshop/skyshop-massagechair")
		                .contact(new Contact("skyshop", "https://github.com/zfjgit/skyshop", "zfjemail@qq.com")).version("1.0.0-SNAPSHOT").build();
	}
}