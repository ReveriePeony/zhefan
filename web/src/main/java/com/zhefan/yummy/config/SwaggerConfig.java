package com.zhefan.yummy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// 扫描包
	private static final String WEB_PACKAGE = "com.zhefan.yummy.controller";
	private static final String MOBILE_PACKAGE = "com.zhefan.yummy.mobileController";
	// 标题
	private static final String TITLE = "YUMMY - API";
	// 描述
	private static final String DESC = "母鸡";
	// 版本
	private static final String VERSION = "1.0.0";
	//
	private static final String TERMS_OF_SERVICEURL = "NO terms of service";
	// 作者信息
	private static final Contact CONTACT = null;
	//
	private static final String LICENSE = "swagger-ui";
	//
	private static final String LICENSE_URL = "https://github.com/ReveriePeony/ZheFan";
	
	@Bean
	public Docket mobileRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(true).groupName("mobile")
				.genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false).forCodeGeneration(true)
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(MOBILE_PACKAGE))
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public Docket webRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(true).groupName("web")
				.genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false).forCodeGeneration(true)
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(WEB_PACKAGE))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(TITLE, // 大标题
				DESC, // 小标题
				VERSION, // 版本
				TERMS_OF_SERVICEURL, CONTACT, // 作者
				LICENSE, // 链接显示文字
				LICENSE_URL// 网站链接
		);
	}

}
