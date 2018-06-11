package com.prenosrobe.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
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
	private static final String TITLE_2 = "Set dostupnih funkcija servera 'Prenos robe'";
	private static final String DESCRIPTION = "REST API for mobile service 'Prenos robe'";
	private static final String DESCRIPTION_2 = "U ovoj dokumentaciji su sadržane sve funkcionalnosti "
			+ "koje server 'Prenos robe' podržava, potpisi HTTP zahteva za korišćenje tih funkcionalnosti "
			+ "i očekivani odgovori na poslate zahteve.";
	private static final String CONTACT = "bojana.curcic17@gmail.com";
	private static final String NAME = "Bojana Curcic";

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION)
				.contact(new Contact(NAME, "", CONTACT)).license(LICENSE_TEXT)
				.version(SWAGGER_API_VERSION).build();
	}

	private Predicate<String> postPaths()
	{
		return or(regex("/user.*"), regex("/impression.*"));
	}

	@Bean
	public Docket productsApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}
}
