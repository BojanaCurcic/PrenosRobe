package com.prenosrobe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig
{
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE_TEXT = "License";
	private static final String TITLE = "REST API 'Prenos robe'";
	private static final String DESCRIPTION = "REST API for mobile service 'Prenos robe'";
	private static final String CONTACT = "bojana.curcic17@gmail.com";
	private static final String NAME = "Bojana Curcic";

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION)
				.contact(new Contact(NAME, "", CONTACT)).license(LICENSE_TEXT)
				.version(SWAGGER_API_VERSION).build();
	}

	@Bean
	public Docket productsApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/api.*")).build();
	}
}
