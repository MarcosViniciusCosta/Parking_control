package com.cloud.parking.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig implements WebMvcConfigurer
{
	private Contact contato()
	{
		return new Contact("Marcos Vinicius","https://www.github.com/MarcosViniciusCosta",
				null);
	}
	
	
	private ApiInfoBuilder informacoesApi()
	{
		ApiInfoBuilder apiINfoBuilder = new ApiInfoBuilder()
				.title("Microsserviço de Medicamento")
				.version("1.0")
				.contact(contato());
	
		return apiINfoBuilder;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	
	
	@Bean
	public Docket detalheApi()
	{
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cloud.parking.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(informacoesApi().build())
				;
		
		return docket;
	}
	
	
	
	
	
}
