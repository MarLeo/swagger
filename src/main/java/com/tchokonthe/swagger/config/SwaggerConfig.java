package com.tchokonthe.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        Contact contact = new Contact("Martin Tchokonthe", "https://github.com/MarLeo", "martintchokonthe@yahoo.fr");
        /*return new ApiInfo(
                "Spring Boot Swagger Example API",
                "Spring Boot Swagger Example API for testing",
                "1.0",
                "Terms of Service",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        );*/

        return new ApiInfoBuilder()
                .title("Spring Boot Swagger Example API")
                .description("Spring Boot Swagger Example API for testing")
                .version("1.0")
                .termsOfServiceUrl("Terms of Service")
                .contact(contact)
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }


    @Bean
    public Docket productApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build();
    }


}
