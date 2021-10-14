package br.fai.bloco7.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${Swagger.enabled}")
	private Boolean swaggerEnabled;

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.fai.bloco7.api.controller")).paths(PathSelectors.any())
				.build().apiInfo(getApiInfo()).enable(swaggerEnabled);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Projeto bloco7").description("API do projeto bloco7").version("1.0.0")
				.build();
	}

}
